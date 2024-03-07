package homeworkModule13;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final Pattern POST_ID_PATTERN = Pattern.compile("\"id\": (\\d+),");

    private static String sendHttpRequest(String endpoint, String method, String requestBody) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        if (requestBody != null) {
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    private static void writeJsonToFile(String json, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int getLatestPostId(int userId) throws IOException {
        String postsEndpoint = "/users/" + userId + "/posts";
        String postsJson = sendHttpRequest(postsEndpoint, "GET", null);

        Matcher matcher = POST_ID_PATTERN.matcher(postsJson);
        int latestPostId = -1;
        while (matcher.find()) {
            latestPostId = Integer.parseInt(matcher.group(1));
        }

        return latestPostId;
    }

    public static void main(String[] args) {
        try {
            String newUserJson = sendHttpRequest("/users", "POST", "{ \"name\": \"John Doe\", \"username\": \"johndoe\", \"email\": \"johndoe@example.com\" }");
            System.out.println("Created User:\n" + newUserJson);

            String updatedUserJson = sendHttpRequest("/users/1", "PUT", "{ \"name\": \"Updated Name\", \"username\": \"updatedusername\", \"email\": \"updatedemail@example.com\" }");
            System.out.println("Updated User:\n" + updatedUserJson);

            int deletedUserId = 2;
            int responseCode = ((HttpURLConnection) new URL(BASE_URL + "/users/" + deletedUserId).openConnection()).getResponseCode();
            if (responseCode == 200) {
                System.out.println("User with id " + deletedUserId + " deleted successfully.");
            } else {
                System.out.println("Failed to delete user with id " + deletedUserId + ". Response code: " + responseCode);
            }

            String allUsersJson = sendHttpRequest("/users", "GET", null);
            System.out.println("All Users:\n" + allUsersJson);

            int userIdToRetrieve = 3;
            String userByIdJson = sendHttpRequest("/users/" + userIdToRetrieve, "GET", null);
            System.out.println("User by Id " + userIdToRetrieve + ":\n" + userByIdJson);

            String usernameToRetrieve = "johndoe";
            String userByUsernameJson = sendHttpRequest("/users?username=" + usernameToRetrieve, "GET", null);
            System.out.println("User by Username " + usernameToRetrieve + ":\n" + userByUsernameJson);

            int userIdForComments = 1;
            int postIdForComments = getLatestPostId(userIdForComments);
            String commentsEndpoint = "/posts/" + postIdForComments + "/comments";
            String commentsJson = sendHttpRequest(commentsEndpoint, "GET", null);
            String fileName = "user-" + userIdForComments + "-post-" + postIdForComments + "-comments.json";
            writeJsonToFile(commentsJson, fileName);
            System.out.println("Comments for latest post of user " + userIdForComments + " written to file: " + fileName);

            int userIdForTodos = 1;
            String todosEndpoint = "/users/" + userIdForTodos + "/todos";
            String todosJson = sendHttpRequest(todosEndpoint, "GET", null);
            System.out.println("Open Todos for user " + userIdForTodos + ":\n" + todosJson);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package homeworkModule12;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task1 {
    public static void main(String[] args) {

        Thread messageThread = new Thread(new MessageDisplay());


        messageThread.start();
    }
}
class MessageDisplay implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000); // чекаємо 5 секунд
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Минуло 5 секунд");
        }
    }
}
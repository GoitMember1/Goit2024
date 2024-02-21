package homeworkModule11;

import java.util.stream.Stream;

public class Task4 {
    public static Stream<Long> generateRandomStream(long seed, long a, long c, long m) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    public static void main(String[] args) {
        long a = 25214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);

        long seed = System.currentTimeMillis(); // можна використати будь-яке початкове значення

        Stream<Long> randomStream = generateRandomStream(seed, a, c, m);

        // Виводимо перші 10 елементів стріму
        randomStream.limit(10).forEach(System.out::println);
    }
}

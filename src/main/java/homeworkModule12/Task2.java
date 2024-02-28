package homeworkModule12;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task2 {
    private static int n = 15;
    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        Thread threadA = new Thread(new Fizz());
        Thread threadB = new Thread(new Buzz());
        Thread threadC = new Thread(new FizzBuzzCheck());
        Thread threadD = new Thread(new Number());

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }

    static class Fizz implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 != 0) {
                    try {
                        queue.put("fizz");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Buzz implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                if (i % 3 != 0 && i % 5 == 0) {
                    try {
                        queue.put("buzz");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class FizzBuzzCheck implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    try {
                        queue.put("fizzbuzz");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Number implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= n; i++) {
                try {
                    String value = queue.take();
                    System.out.println(value != null ? value : i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

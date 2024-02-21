package homeworkModule11;


import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
public class Task5 {
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> iteratorFirst = first.iterator();
        Iterator<T> iteratorSecond = second.iterator();

        Iterator<T> iterator = new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return iteratorFirst.hasNext() && iteratorSecond.hasNext();
            }

            @Override
            public T next() {
                return iteratorFirst.next();
            }
        };

        Spliterator<T> spliterator = Spliterators.spliteratorUnknownSize(iterator, 0);

        return StreamSupport.stream(spliterator, false);
    }

    public static void main(String[] args) {
        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4);
        Stream<Integer> stream2 = Stream.of(5, 6, 7);

        Stream<Integer> zippedStream = zip(stream1, stream2);
        zippedStream.forEach(System.out::println);
    }
}

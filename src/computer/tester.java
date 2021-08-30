package computer;

import java.util.concurrent.atomic.AtomicReference;


public class tester {
    public static void main(String[] args) {
        AtomicReference<Integer> a = new AtomicReference<>(15);
        System.out.println(a);

        a.getAndSet(a.get() + 5);
        System.out.println(a);
    }
}

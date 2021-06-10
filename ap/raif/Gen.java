package ap.raif;

import java.util.concurrent.atomic.AtomicLong;

public class Gen {

    public static AtomicLong counter = new AtomicLong(0);

    public static int nextId() {

        long m_next = counter.incrementAndGet();

        return (int) m_next;

    }

    public static void reset() {

        counter = new AtomicLong(0);

    }

    public static void reset(int value) {

        counter = new AtomicLong(value);

    }

    public static int get() {

        return (int)counter.get();

    }

}

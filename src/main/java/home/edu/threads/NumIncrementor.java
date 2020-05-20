package home.edu.threads;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey on 5/20/2020.
 */
public class NumIncrementor {

    public static void main(String[] args) {
        Incrementor incrementor = new Incrementor();
        Thread t1 = new Thread(incrementor);
        Thread t2 = new Thread(incrementor);
        t1.start();
        t2.start();
    }

    static class Incrementor implements Runnable {

        volatile int counter = 0;
        ReentrantLock counterLock = new ReentrantLock(true);

        @Override
        public void run() {
            while ( counter < 2) {
                incrementCounter();
            }
        }

        public void incrementCounter() {
            counterLock.lock();
            System.out.println(Thread.currentThread().getName() + "   " + counter);
            counter++;
            counterLock.unlock();
        }

    }

}

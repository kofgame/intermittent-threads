package home.edu.threads;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andrey on 5/20/2020.
 */
public class NumIncrementorUsingLocks {

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
            while ( counter < 20) {
                try {
                    incrementCounter();
                } catch (InterruptedException e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }

        public void incrementCounter() throws InterruptedException {
            counterLock.lock();
            System.out.println(Thread.currentThread().getName() + "   " + counter);
            counter++;
//            Thread.sleep(1000);
            counterLock.unlock();
        }

    }

}

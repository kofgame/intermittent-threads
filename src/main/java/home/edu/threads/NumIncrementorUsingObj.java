package home.edu.threads;

/**
 * Created by Andrey on 5/20/2020.
 */
public class NumIncrementorUsingObj {
    private static volatile int number = 0;
    private static volatile boolean flag = false;
    private static Object obj = new Object();

    public static void main(String[] args) {
        synchronizeUsingObject();
    }

    private static void synchronizeUsingObject() {
        new Thread(() -> {
            while (number < 20) {
                try {
                    synchronized (obj) {
                        if (flag) obj.wait();
                        System.out.println("Thr 1: " + number++);
                        Thread.sleep(1000);
                        flag = true;
                        obj.notify();
                    }
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (number < 20) {
                try {
                    synchronized (obj) {
                        if (!flag) obj.wait();
                        System.out.println("Thr 2: " + number++);
                        Thread.sleep(1000);
                        flag = false;
                        obj.notify();
                    }
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }).start();

    }

}

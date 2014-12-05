package app.android.development.bangkokguy.mthread;

import android.util.Log;

/**
 * Created by bangkokguy on 2014.11.18..
 *
 * Java Program to demonstrate how to use Thread in Java with Example
 * Here two threads are provided Runnable interface implementation using
 * anonymous class and when started they will print Thread's name.
 *
 */

public class ThreadTest {

    private final static String TAG = "MThread.ThreadTest";
    private final static Object lock1 = new Object();

    public static void main(){

        //two threads in Java which runs in Parallel
        Thread threadA = new Thread(new Runnable(){
            public void run(){
                synchronized(lock1) {
                    for (int i = 0; i < 10; i++) {
                        Log.d(TAG, "This is thread : " + Thread.currentThread().getName());
                    }
                }
            }
        }, "Thread A");

        //Runnable interface is implemented using Anonymous Class
        Thread threadB = new Thread(new Runnable(){
            public void run(){
                synchronized(lock1) {
                    for(int i =0; i<10; i++) {
                        Log.d(TAG, "This is thread : " + Thread.currentThread().getName());
                    }
                }
            }
        }, "Thread B");

        //starting both Thread in Java

        threadA.start(); //start will call run method in new thread
        threadB.start();

    }

}
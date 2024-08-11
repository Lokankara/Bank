package com.wallet.tutor.module03;

public class Terminate implements Runnable {
    // this is a shared flag visible for both threads
    boolean shouldTerminate = false;
// we want to interrupt the thread


    public void run() {
        while (true) {
            shouldTerminate = true;
// inside the run method of the thread to be terminated:
            if (shouldTerminate) return;
        }
    }
}

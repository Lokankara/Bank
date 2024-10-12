package com.wallet.tutor.module04;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ExecutorServiceTest {
    Set<String> currentThreads = Collections.synchronizedSet(new HashSet<>());

    public void sleep(long timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void logCurrentThreads() {
        StringBuilder currentThreadsStr = new StringBuilder();
        List<String> threads = new ArrayList<>(currentThreads);
        for (String str : threads) {
            currentThreadsStr.append(str).append(", ");
        }
        log.info("size:" + currentThreads.size() +
                ", current thread pool: " + currentThreadsStr.toString());
    }

    class TestThread implements Runnable {
        String threadName;

        public TestThread(String threadName) {
            this.threadName = threadName;
        }

        @Override
        public void run() {
            currentThreads.add(threadName);
            logCurrentThreads();
            sleep(10);
            currentThreads.remove(threadName);
            logCurrentThreads();
        }
    }

    @Test
    public void testThread() {
        long start = new Date().getTime();

        List<Thread> threads = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(new TestThread("t" + i)));
        }

//		ExecutorService executorService = Executors.newSingleThreadExecutor();
//		ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(threads.get(i));
//			executorService.submit(threads.get(i));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
//			executorService.awaitTermination(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
        }
        executorService.shutdownNow();

        long time = new Date().getTime() - start;
        log.info("Time of work:" + time);
        assertTrue(currentThreads.isEmpty());
    }
}

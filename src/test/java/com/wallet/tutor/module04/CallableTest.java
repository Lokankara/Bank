package com.wallet.tutor.module04;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/**
 * Interface Callable <T> allows to create threads that return the execution result T.
 * 1) Try to run the class and look at the results and execution time. Replace the call of newSingleThreadExecutor () to newFixedThreadPool() and compare the execution time.
 * 2) Instead of executorService.execute() use executorService.submit(). Save results in the array of Future objects.
 * 3) Try to stop the execution of the first 5 streams using the method cancel(). Handle CancellationException.
 * 4) Before finishing the thread add sleep for 15 milliseconds. Experiment with the size of the pool: how many threads will complete the work, and how many do not (set, for example, pool of 2).
 * On termination of the thread, add logging, informing if the work was finished: log ("canceling thread" + i + ", isDone =" + results.get(i).isDone());
 * 5) Add a message after the completion of sleep of StringGenerator: log ("thread is finished:" + allStrings [index]);
 * Try to experiment with the parameter of cancel() method: set true or false.
 */

@Slf4j
public class CallableTest {
    static StringBuffer buf = new StringBuffer();

    static void log(String s) {
        buf.append(s).append("\n");
    }

    public class StringGenerator implements Callable<String> {

        public String call() throws Exception {
            String[] allStrings = {"Cow", "Goose", "Cat", "Dog", "Elephant", "Rabbit", "Snake", "Chicken", "Horse", "Human"};
            int index = (int) (Math.random() * 100) / 10;

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return allStrings[index];
        }
    }

    @Test
    public void testCallable() {
        long start = new Date().getTime();

        ArrayList<Future<String>> results = new ArrayList<>();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
//		ExecutorService executorService = Executors.newFixedThreadPool(3);
//		ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            results.add(executorService.submit(new StringGenerator()));
        }

        StringBuilder resultStr = new StringBuilder();
        for (Future<String> result : results) {
            try {
                resultStr.append(result.get()).append(" ");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        log.info(resultStr.toString());

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long time = new Date().getTime() - start;

        log.info("Time of work: " + time);

        log.info(String.valueOf(buf));
    }

}

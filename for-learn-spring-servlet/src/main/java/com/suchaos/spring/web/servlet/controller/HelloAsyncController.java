package com.suchaos.spring.web.servlet.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * HelloAsyncController
 *
 * @author suchao
 * @date 2020/8/21
 */
@RestController
@EnableScheduling
public class HelloAsyncController {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    private static final BlockingQueue<DeferredResult<String>> BLOCKING_DEQUE =
            new ArrayBlockingQueue<>(5);

    @Scheduled(fixedDelay = 5000)
    public void process() {
        System.out.println("start scheduled");

        DeferredResult<String> result = null;
        while (true) {
            try {
                result = BLOCKING_DEQUE.take();
                int time = ThreadLocalRandom.current().nextInt(100);
                Thread.sleep(time);
                System.out.println(atomicInteger.get() + " sleeping time is " + time);
                result.setResult("result for " + atomicInteger.get() + ", " + time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @GetMapping("/hello")
    public DeferredResult<String> helloDeferredResult() {
        DeferredResult<String> deferredResult = new DeferredResult<>(50L);

        // 注释掉下面的一行，会导致 onTimeout 也被执行
        //deferredResult.setResult("hello, DeferredResult");

        BLOCKING_DEQUE.offer(deferredResult);

        printWithThreadName("hello");
        deferredResult.onCompletion(() -> {
            printWithThreadName("deferred onCompletion " + atomicInteger.getAndIncrement());
        });
        deferredResult.onTimeout(() -> {
            printWithThreadName("deferred onTimeout " + atomicInteger.get());
        });

        return deferredResult;
    }

    @GetMapping("/hello-callable")
    public Callable<String> helloCallable() {
        final long start = System.currentTimeMillis();
        printWithThreadName("hello-callable");
        return () -> {
            long time = System.currentTimeMillis() - start;
            printWithThreadName("spend " + time);
            return "spend" + time;
        };
    }

    @GetMapping("/hello-completionStage")
    public CompletionStage<String> completionStage() {
        final long start = System.currentTimeMillis();
        printWithThreadName("hello-completionStage");
        return CompletableFuture.supplyAsync(() -> {
            long time = System.currentTimeMillis() - start;
            printWithThreadName("spend " + time);
            return "completionStage spend" + time;
        });
    }

    public static void printWithThreadName(Object object) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ": " + object);
    }
}

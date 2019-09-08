package org.springboot;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        scheduledThreadPool.scheduleWithFixedDelay(()->System.out.println(LocalDateTime.now()), 0L, 10L, TimeUnit.SECONDS);
//        scheduledThreadPool.schedule(() -> System.out.println(LocalDateTime.now()), 10, TimeUnit.SECONDS);

//        TimeUnit.SECONDS.sleep(60);

        System.out.println("KKK");

        System.out.println("KKK");
        System.out.println("KKK");
        System.out.println("KKK");
        System.out.println("KKK");
        System.out.println("KKK");
        System.out.println("KKK");
    }
}

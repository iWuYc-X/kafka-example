package com.iwuyc.example.kafka.cli;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import com.iwuyc.example.kafka.cli.producer.iKafkaProducer;

/**
 * Kafka Bootstrap
 *
 */
public class ProducerBootstrap {

    public static void main(String[] args) {
        iKafkaProducer producer = new iKafkaProducer();

        try (Scanner sc = new Scanner(System.in)) {
            productTopic(producer);
            sc.nextLine();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void productTopic(final iKafkaProducer producer) {
        new Thread(() -> {
            int i = 0;
            while (true) {
                producer.produce("test", "val-" + i);
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(10));
                System.out.println("val-" + i);
                i++;
            }
        }).start();

    }
}

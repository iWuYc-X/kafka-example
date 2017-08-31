package com.iwuyc.example.kafka.cli;

import com.iwuyc.example.kafka.cli.consumer.iKafkaConsumer;

public class ConsumerBootstrap {

    public static void main(String[] args) {
        iKafkaConsumer<String, String> consumer = new iKafkaConsumer<>("test", "test1", "123");
        consumer.addCustomer((item) -> {
            System.out.println(item.value());
        });

        consumer.consume();
    }
}

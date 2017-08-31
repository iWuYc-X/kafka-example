package com.iwuyc.example.kafka.cli.consumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.function.Consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class iKafkaConsumer<T, V> {

    org.apache.kafka.clients.consumer.Consumer<T, V> consumer;
    private Collection<Consumer<ConsumerRecord<T, V>>> customers = new ArrayList<>();

    public iKafkaConsumer(String... topics) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topics));
    }

    public void consume() {
        ConsumerRecords<T, V> consumerRecords = null;
        while (true) {
            consumerRecords = consumer.poll(10000);
            consumerRecords(consumerRecords);
        }
    }

    private void consumerRecords(ConsumerRecords<T, V> consumerRecords) {
        for (ConsumerRecord<T, V> consumerRecord : consumerRecords) {
            for (Consumer<ConsumerRecord<T, V>> item : this.customers) {
                item.accept(consumerRecord);
            }
        }
    }

    public void addCustomer(Consumer<ConsumerRecord<T, V>> cumstomer) {
        this.customers.add(cumstomer);
    }
}

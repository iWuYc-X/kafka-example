package com.iwuyc.example.kafka.cli.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class iKafkaProducer {
  private final Producer<String, String> producer;
  public final static String TOPIC = "TEST-TOPIC";

  public iKafkaProducer() {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("acks", "all");
    props.put("retries", 0);
    props.put("batch.size", 16384);
    props.put("linger.ms", 1);
    props.put("buffer.memory", 33554432);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    producer = new KafkaProducer<>(props);
  }

  public void produce(String topic, String value) {
    ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, value);
    producer.send(record);
  }
}
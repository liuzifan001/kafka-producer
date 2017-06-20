package com.jointsky.bigdata.test;

import com.jointsky.bigdata.api.EdpsKafkaServiceImpl;
import com.jointsky.bigdata.util.PropertiesLoader;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Created by LiuZifan on 2017/6/14.
 */

public class ConsumerDEMO {
    private KafkaProducer producer = null;
    private static PropertiesLoader loader = new PropertiesLoader("kafka.properties");

    public static void main(String[] args) {
        Properties consumerProps = new Properties();
        consumerProps.setProperty("bootstrap.servers",loader.getProperty("bootstrap.servers"));
        consumerProps.setProperty("key.deserializer", loader.getProperty("key.deserializer"));
        consumerProps.setProperty("value.deserializer",loader.getProperty("value.deserializer"));
        consumerProps.setProperty("enable.auto.commit",loader.getProperty("enable.auto.commit"));                  //自动提交位移
        consumerProps.setProperty("auto.commit.interval.ms",loader.getProperty("auto.commit.interval.ms"));
       // consumerProps.setProperty("session.timeout.ms",loader.getProperty("session.timeout.ms"));
        consumerProps.setProperty("group.id","test");

        KafkaConsumer consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Arrays.asList("gas_realtimedata"));
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        final int minBatchSize = 20;

        try {
        while (true) {
            ConsumerRecords<String,String> records = consumer.poll(100);
            for (ConsumerRecord<String,String> record: records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                for (ConsumerRecord<String,String> record: buffer) {
                    System.out.printf("partition = %d offset = %d, key = %s, value = %s ", record.partition(), record.offset(), record.key(), record.value() + "\n");
                    Thread.sleep(100);
                }

                consumer.commitSync();    //手动提交
                System.out.println("AutoCommit!");
                buffer.clear();
            }
        }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("finally closed the connect");
            consumer.close();
        }

    }
}

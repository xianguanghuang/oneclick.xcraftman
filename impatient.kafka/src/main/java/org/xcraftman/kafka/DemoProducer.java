package org.xcraftman.kafka;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

import java.util.Properties;

/**
 * Created by xianguang on 10/3/16.
 */
public class DemoProducer {

    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("metadata.broker.list", "172.27.196.1:9092");
        properties.put("serializer.class",StringEncoder.class.getName());
        properties.put("producer.type","sync");
        properties.put("batch.size", "10");
        properties.put("queue.time", "1");

        ProducerConfig config =new ProducerConfig(properties);

        Producer<String,String> producer = new Producer<String, String>(config);
        for (int i = 0; i < 1; i++){
            for(int j = 0 ; j< 3; j++){
                String key =String.valueOf(j);
                String message = String .format("Key = %s, message # = %s", key, i);
                producer.send(new KeyedMessage<String, String>(DemoConsumer.TOPIC, key, message));
                System.out.println("Sent message : " + message);
            }
        }

        producer.close();

    }
}
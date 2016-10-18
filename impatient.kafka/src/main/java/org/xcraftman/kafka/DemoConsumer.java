package org.xcraftman.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by xianguang on 10/3/16.
 */
public class DemoConsumer {

    public static final String TOPIC = "test";

    public static void main(String[] args){

        String groupId = "group1_laptop";
        Properties properties = new Properties();
        properties.put("zookeeper.connect", "172.27.196.1:2181");
        properties.put("autocommit.enable","true");
        properties.put("client.id","demoConsumer");
        properties.put("group.id", groupId);
        String consumerId = "consumer1_laptop";


        ConsumerConfig consumerConfig = new ConsumerConfig(properties);
        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(TOPIC, 1);

        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);

        KafkaStream<byte[],byte[]> stream1 = consumerMap.get(TOPIC).get(0);

        ConsumerIterator<byte[], byte[]> it1 = stream1.iterator();
        while (it1.hasNext()){
            MessageAndMetadata<byte[], byte[]> messageAndMetadata = it1.next();
            String message =
                    String.format("Consumer ID:%s, Topic:%s, GroupID:%s, PartitionID:%s, Offset:%s, MessagePayload:%s",
                            consumerId,
                            messageAndMetadata.topic(), groupId, messageAndMetadata.partition(),
                            messageAndMetadata.offset(), new String(messageAndMetadata.message()));
            System.out.println(message);


            consumerConnector.commitOffsets();
        }



    }
}

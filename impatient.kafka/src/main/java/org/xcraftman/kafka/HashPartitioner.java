package org.xcraftman.kafka;

/**
 * Created by Administrator on 2016/10/18.
 */
import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * Created by xianguang on 10/3/16.
 */
public class HashPartitioner  implements Partitioner{

    public HashPartitioner(VerifiableProperties verifiableProperties) {
    }

    public int partition(Object key, int numPartitions) {

        if (key instanceof Integer){
            return Math.abs(Integer.parseInt(key.toString())  % numPartitions);
        }
        return Math.abs(key.hashCode() % numPartitions);
    }
}
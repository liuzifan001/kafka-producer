package com.jointsky.bigdata.util;



import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 消息生产时的分发函数
 * Created by LiuZifan on 2017/6/15.
 */
public class ProducerPartitioner implements Partitioner {
    private static Logger LOG = LoggerFactory.getLogger(ProducerPartitioner.class);
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // TODO Auto-generated method stub
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        if (key == null) {
            Random random = new Random();
            System.out.println("key is null ");
            LOG.info("the message sendTo topic:" + topic + " and the partitionNum:" + random.nextInt(numPartitions));
            return random.nextInt(numPartitions);
        }
        else {
            int partitionNum = 0;
            try {
                partitionNum = Integer.parseInt((String) key);
            } catch (Exception e) {
                partitionNum = key.hashCode();
            }
            LOG.info("the message sendTo topic:" + topic + " and the partitionNum:" + Math.abs(partitionNum % numPartitions));
            return Math.abs(partitionNum % numPartitions);
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}

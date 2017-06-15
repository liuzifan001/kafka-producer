package com.jointsky.bigdata.util;

import kafka.producer.Partitioner;

import java.util.Random;

/**
 * 消息生产时的分发函数
 * Created by LiuZifan on 2017/6/15.
 */
public class ProducerPartitioner implements Partitioner {


    @Override
    public int partition(Object key, int numPartitions) {
        System.out.print("partitions number is "+numPartitions+"   ");
        if (key == null) {
            Random random = new Random();
            System.out.println("key is null ");
            return random.nextInt(numPartitions);
        }
        else {
            int result = Math.abs(key.hashCode())%numPartitions; //很奇怪，
            //hashCode 会生成负数，所以加绝对值
            System.out.println("key is "+ key+ " partitions is "+ result);
            return result;
        }
    }
}

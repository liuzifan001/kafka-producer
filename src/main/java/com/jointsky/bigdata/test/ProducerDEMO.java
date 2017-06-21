package com.jointsky.bigdata.test;

import com.jointsky.bigdata.api.EdpsKafkaService;
import com.jointsky.bigdata.api.EdpsKafkaServiceImpl;
import com.jointsky.bigdata.util.TopicUtil;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Created by LiuZifan on 2017/6/14.
 */

public class ProducerDEMO {
    public static void main(String[] args) {
        EdpsKafkaService edps = new EdpsKafkaServiceImpl();
        try {
            edps.establishConnect();
            String topic = "Test620";
          //  TopicUtil.createTopic(topic,4,1);     //创建4个分区的topic


            for (int i=1000;i<=10000;i++) {
                String str = "Message" + i;
                //两个泛型，第一个指定key的泛型，第二个指定value的泛型
                ProducerRecord<String,String> record = new ProducerRecord<String, String>(topic,null,str);
                edps.send(record);
                System.out.println("已发送: " + str );
                Thread.sleep(20);
            }

            edps.closeConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.jointsky.bigdata.test;

import com.jointsky.bigdata.api.EdpsKafkaService;
import com.jointsky.bigdata.api.EdpsKafkaServiceImpl;
import com.jointsky.bigdata.util.TopicUtil;

/**
 * Created by LiuZifan on 2017/6/14.
 */

public class ProducerDEMO {
    public static void main(String[] args) {
        EdpsKafkaService edps = new EdpsKafkaServiceImpl();
        try {
            edps.establishConnect();
            String topic = "TeatPartition";
            TopicUtil.createTopic(topic,4,1);     //创建4个分区的topic


/*            for (int i=1000;i<=10000;i++) {
                String str = "Message" + i;
                MessageData m = new MessageData();
                m.setTopic(topic);
                m.setData(str);
                edps.send(m);
                System.out.println("已发送message: " + m.getData() );
                Thread.sleep(1000);
            }*/

            edps.closeConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

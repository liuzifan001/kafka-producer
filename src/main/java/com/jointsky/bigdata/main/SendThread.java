package com.jointsky.bigdata.main;

import com.jointsky.bigdata.api.EdpsKafkaService;
import com.jointsky.bigdata.api.EdpsKafkaServiceImpl;
import com.jointsky.bigdata.util.LoadFileUtil;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.File;
import java.util.List;

/**
 * Created by LiuZifan on 2017/5/18.
 */
public class SendThread extends Thread {
    private String topic;
    private static LoadFileUtil loadFileUtil = new LoadFileUtil();

    public SendThread (String topic){
        this.topic = topic;
    }

    @Override
    public void run() {
        EdpsKafkaService kafkaService = new EdpsKafkaServiceImpl();
        List<File> files = loadFileUtil.getSortedFile(topic);                                 //获取主题对应文件列表
        if (files.size() != 0) {
            try {
                kafkaService.establishConnect();
                List<ProducerRecord> messageList = loadFileUtil.getFileContent(topic, files.get(0));      //仅取文件列表中最旧的文件中的消息进行发送
                kafkaService.send(messageList);


            } catch (Exception e) {
                e.printStackTrace();
            }

            files.get(0).delete();           //传输完成后删除文件
            kafkaService.closeConnect();      //关闭此producer
        }

    }
}

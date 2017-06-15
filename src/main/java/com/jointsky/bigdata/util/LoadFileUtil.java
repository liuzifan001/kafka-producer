package com.jointsky.bigdata.util;

import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.File;
import java.util.*;

/**
 * 获取目标目录中的文件，并读取其中的消息
 * Created by LiuZifan on 2017/5/17.
 */
public class LoadFileUtil {

    private static PropertiesLoader loader = new PropertiesLoader("dataFile.properties");

    /**
     * 获取指定主题的对应文件夹目录下的所有文件，并按修改时间排序
     * @param topic 主题
     * @return 排序好的文件列表
     */
    public  List<File> getSortedFile(String topic) throws NullPointerException {
        if (loader.getProperty(topic) == null || loader.getProperty(topic).isEmpty()) {
            return new ArrayList<File>();             //若提供的topic无法检索到路径则返回空列表
        }
        File realFile = new File(loader.getProperty("rootpath") + loader.getProperty(topic));
        //System.out.println(loader.getProperty("rootpath") + loader.getProperty(topic));
        List<File> files = new ArrayList<>();
        if (realFile.isDirectory()){
            File[] subFiles = realFile.listFiles();
            for (File file :subFiles) {
                files.add(file);
            }
        }
        Collections.sort(files, new Comparator<File>() {
            @Override
            public int compare(File file1, File file2) {
                if (file1.lastModified() < file2.lastModified()) {
                    return -1;
                } else if (file1.lastModified() == file2.lastModified()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return files;
    }

    /**
     *  将消息文件内容按行封装为List<MessageData>
     * @param topic 消息主题
     * @param file 文件
     * @return 返回基于文件内容的消息List
     */
    public List<ProducerRecord> getFileContent(String topic, File file) throws Exception {
        Scanner sc = new Scanner(file);
        List<ProducerRecord> producerRecordList = new ArrayList<>();
        while (sc.hasNextLine()){
            String data = sc.nextLine();
            ProducerRecord<String,String> producerRecord = new ProducerRecord<String, String>(topic,null,data);    //封装消息
            producerRecordList.add(producerRecord);
        }
        sc.close();
        return producerRecordList;
    }

}

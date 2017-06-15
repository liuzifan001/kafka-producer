package com.jointsky.bigdata.util;


import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.server.ConfigType;
import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import java.util.Map;
import java.util.Properties;

/**
 * 操作Kafka topic的工具类
 * Created by LiuZifan on 2017/6/15.
 */
public class TopicUtil {
    private static PropertiesLoader loader = new PropertiesLoader("kafka.properties");
    private static String zk = loader.getProperty("zookeeper.connect");
    private static int sessionTimeoutMs = 10000;
    private static int connectionTimeoutMs = 10000;
    private static ZkClient zkClient = new ZkClient(zk,sessionTimeoutMs,connectionTimeoutMs,ZKStringSerializer$.MODULE$);


    /**
     * 创建topic
     * @param topic 消息topic名称
     * @param numPartitions topic分区数
     * @param replicationFactor topic的复制因子 (不大于broker总数)
     * @throws Exception
     */
    public static void createTopic(String topic, int numPartitions, int replicationFactor) throws Exception {
        ZkUtils zkUtils = new ZkUtils(zkClient,new ZkConnection(zk),false);
        AdminUtils.createTopic(zkUtils, topic, numPartitions, replicationFactor,new Properties(),RackAwareMode.Enforced$.MODULE$);
        zkUtils.close();
    }

    /**
     * 查询topic的属性信息 (还有bug)
     * @param topic : topic名称
     * @return topic的信息
     */
    public String getTopicInfo(String topic) {
        ZkUtils zkUtils = new ZkUtils(zkClient,new ZkConnection(zk),false);
        Properties props = AdminUtils.fetchEntityConfig(zkUtils, ConfigType.Topic(), topic);
        String topicInfo = "";
        for (Map.Entry entry: props.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            topicInfo += key + " = " + value + " ;";
        }
        zkUtils.close();
        return topicInfo;
    }

    /**
     * 删除topic
     * @param topic topic名称
     */
    public void deleteTopic(String topic) throws Exception {
        ZkUtils zkUtils = new ZkUtils(zkClient,new ZkConnection(zk),false);
        AdminUtils.deleteTopic(zkUtils,topic);
    }









}

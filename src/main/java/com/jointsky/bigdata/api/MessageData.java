package com.jointsky.bigdata.api;

/**
 * 消息封装体
 * Created by  on 2017/5/16.
 */
public class MessageData {
    private String topic;    // 主题

    private String data;     // 消息内容

    public String getTopic() {
        return topic;
    }

    public MessageData setTopic(String topic) {
        this.topic = topic;
        return this;
    }


    public String getData() {
        return data;
    }

    public MessageData setData(String data) {
        this.data = data;
        return this;
    }
}

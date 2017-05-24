package com.jointsky.bigdata.main;

import com.jointsky.bigdata.api.EdpsKafkaService;
import com.jointsky.bigdata.api.EdpsKafkaServiceImpl;
import com.jointsky.bigdata.api.MessageData;
import com.jointsky.bigdata.util.LoadFileUtil;
import com.jointsky.bigdata.util.PropertiesLoader;

import java.io.File;
import java.util.List;

/**
 * Created by LiuZifan on 2017/5/17.
 */
public class sendToKafka {

    public static void main(String[] args) {
        Thread gasRealTime = new SendThread("gas_realtimedata");
        Thread gasMinute = new SendThread("gas_minutedata");
        Thread gasDay = new SendThread("gas_daydata");
        Thread gasHour = new SendThread("gas_hourdata");

        Thread waterRealTime = new SendThread("water_realtimedata");
        Thread waterMinute = new SendThread("water_minutedata");
        Thread waterDay = new SendThread("water_daydata");
        Thread waterHour = new SendThread("water_hourdata");

        gasRealTime.start();
        gasMinute.start();
        gasHour.start();
        gasDay.start();

        waterRealTime.start();
        waterMinute.start();
        waterHour.start();
        waterDay.start();


    }
}

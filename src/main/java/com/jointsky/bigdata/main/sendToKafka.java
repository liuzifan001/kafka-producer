package com.jointsky.bigdata.main;

/**
 * 进行一次数据发送，使用时可指定周期进行发送
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
/*            gasHour.start();
            gasDay.start();

            waterRealTime.start();
            waterMinute.start();
            waterHour.start();
            waterDay.start();*/

        }
    }


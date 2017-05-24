package com.jointsky.bigdata.api.test;

import com.jointsky.bigdata.api.MessageData;
import com.jointsky.bigdata.util.LoadFileUtil;

import java.io.File;
import java.util.List;

/**
 * 测试LoadFileUtil类
 * Created by LiuZifan on 2017/5/17.
 */
public class LoadFileUtilTest {
    public static void main(String[] args) throws Exception {
        LoadFileUtil loadFileUtil = new LoadFileUtil();
        List<File> aa = loadFileUtil.getSortedFile("gas_realtimedata");
        for (File f: aa) {
            System.out.println(f.getName());
        }
        File file1 = aa.get(aa.size()-1);
        List<MessageData> bb = loadFileUtil.getFileContent("gas_realtimedata",file1);
        for (MessageData m: bb) {
            System.out.println("Message: " + m.getData());
        }
    }

}

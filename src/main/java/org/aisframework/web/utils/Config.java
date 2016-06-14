package org.aisframework.web.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by lenovo on 2016/5/16.
 */
public class Config {
    public static Map<String, String> getConfigMap() {
        String path = getProPath();
        File file = new File(path +File.separator+ "config.ini");
        if(!file.exists())
        {
            System.out.println("没有找到config.ini文件，默认扫描所有类");
            return new HashMap<String,String>();
        }
        Scanner sc = null;

        try {
            sc = new Scanner(new BufferedInputStream(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<String,String> config = new HashMap<String,String>();

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(!line.startsWith("#") && line != null && !"".equals(line)) {
                line = line + "=";
                String[] con = line.split("=");
                config.put(con[0].trim(), con[1].trim());
            }
        }

        return config;
    }

    public static String getProPath() {
        String path = getPath();
        path = path.replace("file:", "");
        return path;
    }



    public static String getPath() {
       // String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
       String path=Config.class.getClassLoader().getResource("").toString();
        return path;
    }

    public static String getConfig(String source) {
        Map<String,String> map = getConfigMap();
        for(String key:map.keySet())
        {
            if(key.equals(source))
                return map.get(key);
        }
        System.out.println("没有在config.ini中配置"+source+",请正确配置，否则默认返回空字符串");
        return "";
    }
}

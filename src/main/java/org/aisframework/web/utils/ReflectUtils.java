package org.aisframework.web.utils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by lenovo on 2016/5/21.
 */
public class ReflectUtils {
    public static boolean classLoaded(String source)
    {
        try {
            Class<?> clazz=Class.forName(source);
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    public static boolean hasField(String className,Map<String,String> reqParaMap)
    {
        Class<?> clazz=ReflectUtils.loadClass(className);
        Field[] fields=clazz.getDeclaredFields();
        Object o=null;
        try {
            o= clazz.newInstance();
            for (Field field : fields) {
                String filedName=field.getName();
                if (reqParaMap.containsKey(filedName)) {
                    return true;
                }
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }
        return false;
    }

    public static Class<?> loadClass(String source)
    {
        Class<?> clazz;
        try {
            clazz=Class.forName(source);
        } catch (ClassNotFoundException e) {
            return null;
        }
        return clazz;
    }

    public static Object getObjectByClassAndReq(String className, Map<String, String> reqParaMap) {
        Class<?> clazz=ReflectUtils.loadClass(className);
        Field[] fields=clazz.getDeclaredFields();
        Object o=null;
        try {
            o= clazz.newInstance();
            for (Field field : fields) {
                String filedName=field.getName();
                if (reqParaMap.containsKey(filedName)) {
                    field.setAccessible(true);
                    field.set(o, reqParaMap.get(filedName));
                }
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }
        return o;
    }
}

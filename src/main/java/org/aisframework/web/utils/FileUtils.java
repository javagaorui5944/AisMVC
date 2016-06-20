package org.aisframework.web.utils;

import java.io.File;
import java.util.Set;

/**
 * Created by gaorui on 2016/5/16.
 */
public class FileUtils {
    public static void getClassSet(String path,Set<Class<?>> classSet,String packageName)
    {
        File file=new File(path);
        for(String fileName:file.list())
        {
            String newPath=path+File.separator+fileName;
            if(new File(newPath).isDirectory()) {
                getClassSet(newPath, classSet,packageName);
            }else{
                if(newPath.endsWith(".class"))
                {
                    try {
                        String className=newPath.substring(newPath.lastIndexOf(StringUtils.modifyPackagePath(packageName))).replace(File.separator,".").replace(".class","");

                        Class<?> cls=Class.forName(className);//,false,Thread.currentThread().getContextClassLoader());
                        classSet.add(cls);
                    } catch (ClassNotFoundException e) {
                        continue;
                    }

                }
            }
        }
    }
}

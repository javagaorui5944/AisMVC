package org.aisframework.web.utils;

import java.io.File;

/**
 * Created by gaorui on 2016/5/16.
 */
public class StringUtils {
    public static String modifyPackagePath(String packageName)
    {
        if(packageName.indexOf(".")>-1)
            return packageName.replace(".", File.separator);
        else
            return packageName;
    }


}

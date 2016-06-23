package org.aisframework.web.classcollection;



import org.aisframework.web.annotation.Controller;
import org.aisframework.web.annotation.MapURL;
import org.aisframework.web.annotation.ResponseBody;
import org.aisframework.web.structure.MethodPro;
import org.aisframework.web.utils.Config;
import org.aisframework.web.utils.FileUtils;
import org.aisframework.web.utils.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by gaorui on 2016/6/14.
 */
public class ClassCollection {
    public static Map<String,MethodPro> methodMap;
    public static Set<Class<?>> classSet;
    public static Map<String,Class<?>> classMap;
    public static void  scanClassSetByPackage(String packageName)
    {
        methodMap=new HashMap<String, MethodPro>();
        classMap=new HashMap<String, Class<?>>();
        classSet=new HashSet<Class<?>>();
        String filePath= Config.getProPath()+ StringUtils.modifyPackagePath(packageName);
        FileUtils.getClassSet(filePath,classSet,packageName);
        for(Class<?> clazz:classSet)
        {
            if(clazz.isAnnotationPresent(Controller.class))
            {
                Method[] methods=clazz.getDeclaredMethods();
                for(Method method:methods)
                {
                    if(method.isAnnotationPresent(MapURL.class))
                    {
                        MapURL mapURL=method.getAnnotation(MapURL.class);
                        boolean b = false;
                        if(method.isAnnotationPresent(ResponseBody.class)){
                           b = true;
                        }
                        MethodPro mp=new MethodPro(method,mapURL.value(),mapURL.RequestMethod(),b);

                        methodMap.put(mapURL.value(),mp);
                        classMap.put(mapURL.value(),clazz);

                    }

                }
            }
        }
     }

    public static Set<Class<?>> getClassSet() {
        return classSet;
    }



    public static Map<String, MethodPro> getMethodMap() {
        return methodMap;
    }

    public static Map<String, Class<?>> getClassMap() {
        return classMap;
    }


}

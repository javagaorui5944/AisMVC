package org.aisframework.web.utils;

/**
 * Created by gaorui on 16/6/5.
 */

import org.aisframework.web.annotation.Controller;
import org.aisframework.web.annotation.MapURL;

import java.lang.reflect.Method;


public class ReflectProcessor {

    public static void parseMethod(final Class<?> clazz,String methodname) throws Exception {
        final Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
        final Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            if(method.getName().equals(methodname)){
                final MapURL my = method.getAnnotation(MapURL.class);
                Class<?>[] parameterTypes = method.getParameterTypes();
                //JSON.parse();
                if (null != my) {
                    method.invoke(obj);
                }
            }

        }
    }
}

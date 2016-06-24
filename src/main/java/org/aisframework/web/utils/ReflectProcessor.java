package org.aisframework.web.utils;

/**
 * Created by gaorui on 16/6/5.
 */


import org.aisframework.web.annotation.MapURL;

import java.lang.reflect.Method;



public class ReflectProcessor {

    public static Object parseMethod(final Class<?> clazz, String methodname, Object[] value) throws Exception {
        if(value == null){
            value = new Object[]{};
        }
        Object o =null;
        final Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
        final Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            if(method.getName().equals(methodname)){
                final MapURL my = method.getAnnotation(MapURL.class);
               // Class<?>[] parameterTypes = method.getParameterTypes();
                if (null != my) {

                    o = method.invoke(obj,value);
                }
            }

        }
        return o;
    }


}

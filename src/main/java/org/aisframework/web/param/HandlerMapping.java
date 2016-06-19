package org.aisframework.web.param;

import org.aisframework.web.structure.MethodPro;
import org.aisframework.web.test.test;
import org.aisframework.web.utils.CollectionUtils;
import org.aisframework.web.utils.ReflectProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by gaorui on 16/6/19.
 */
public class HandlerMapping {
    public static void  HandlerMapping(HttpServletRequest req,HttpServletResponse resp, Map<String,MethodPro> methodProMap, String key) {
        try {
        List<String> paramlist = MethodResolver.getMethodNames("org.aisframework.web.test.test", key);
        Map params = req.getParameterMap();
        MethodPro methodPro = methodProMap.get(key);
        Method method = methodPro.getMethod();
        List<String> classNames = CollectionUtils.classArrToStringList(method.getParameterTypes());
        Object[] invokeParamVulue = MethodResolver.paramarray(paramlist, classNames, req, resp, null, params);;
        Method urlmethod = methodProMap.get(key).getMethod();

            if (urlmethod.getReturnType().getName().equals("java.lang.String")) {

                String uri = ReflectProcessor.parseMethod(test.class, key, invokeParamVulue).toString();
                req.getRequestDispatcher("WEB-INF/" + uri + ".html").forward(req, resp);
                return;

            } else if (methodProMap.get(key).getAjax()) {

                Object o = ReflectProcessor.parseMethod(test.class, key, invokeParamVulue);
                resp.getWriter().print(o);
                return;
            }

            ReflectProcessor.parseMethod(test.class, key, invokeParamVulue);
            return ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package org.aisframework.web.servlet;

import org.aisframework.web.classcollection.ClassCollection;
import org.aisframework.web.param.MethodResolver;
import org.aisframework.web.structure.MethodPro;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import  org.aisframework.web.test.test;
import org.aisframework.web.utils.CollectionUtils;
import org.aisframework.web.utils.ReflectProcessor;


public class AisDispatcherServlet extends HttpServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		ClassCollection.scanClassSetByPackage("org.aisframework.web.test");

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo=req.getServletPath();
		Map<String,MethodPro> methodProMap=ClassCollection.getMethodMap();
		Map<String,Class<?>> classMap=ClassCollection.getClassMap();


		Iterator it=methodProMap.entrySet().iterator();

		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			String key= entry.getKey().toString();
			if(("/"+key+".do").equals(pathInfo)){
				try {
					Method method1 = methodProMap.get(key).getMethod();
					if(method1.getReturnType().getName().equals("java.lang.String")){
						String uri = ReflectProcessor.parseMethod(test.class,key,null).toString();

						req.getRequestDispatcher("WEB-INF/"+uri+".html").forward(req,resp);
						return;
					}
					else if(methodProMap.get(key).getAjax()){
						Object o = ReflectProcessor.parseMethod(test.class,key,null);

						resp.getWriter().print(o);
						return;
					}

					List<String> paramlist = MethodResolver.getMethodNames("org.aisframework.web.test.test",key);
					Map params  = req.getParameterMap();

					MethodPro methodPro = methodProMap.get((pathInfo.replaceAll("/","")).split("\\.")[0]);
					Method method=methodPro.getMethod();
					List<String> classNames= CollectionUtils.classArrToStringList(method.getParameterTypes());
					Object[] invokeParamVulue = MethodResolver.paramarray(paramlist,classNames,req,resp,null,params);


					ReflectProcessor.parseMethod(test.class,key,invokeParamVulue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}


}

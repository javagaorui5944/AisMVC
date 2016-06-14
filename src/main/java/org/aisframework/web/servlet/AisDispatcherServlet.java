package org.aisframework.web.servlet;

import org.aisframework.web.classcollection.ClassCollection;
import org.aisframework.web.structure.MethodPro;
import org.aisframework.web.utils.ReflectProcessor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import  org.aisframework.web.test.test;


public class AisDispatcherServlet extends HttpServlet {

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		long start=System.currentTimeMillis();
		ClassCollection.scanClassSetByPackage("org.aisframework.web.test");
		System.out.println("class initalized in " + (System.currentTimeMillis() - start) + " ms");

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo=req.getServletPath();
		Map<String,MethodPro> methodProMap=ClassCollection.getMethodMap();
		Map<String,Class<?>> classMap=ClassCollection.getClassMap();
		//System.out.println(methodProMap+"----"+classMap);
		//System.out.println("pathInfo"+pathInfo);

		Iterator it=methodProMap.entrySet().iterator();

		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			String key= entry.getKey().toString();
			MethodPro value= (MethodPro)entry.getValue();
			if(("/"+key+".do").equals(pathInfo)){
				try {
					ReflectProcessor.parseMethod(test.class,key);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}


}

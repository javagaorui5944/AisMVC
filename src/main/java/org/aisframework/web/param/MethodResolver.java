package org.aisframework.web.param;


import org.aisframework.web.asm.ReadMethodArgNameClassVisitor;
import org.aisframework.web.structure.ModelMap;
import org.aisframework.web.utils.Config;
import org.objectweb.asm.ClassReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

public class MethodResolver {



        public static List<String> getMethodNames(String className,String methodName) throws IOException {
            List<String> list=new ArrayList<String>();
            String cn= Config.getProPath()+className.replace(".", "/")+".class";
            InputStream is=new FileInputStream(new File(cn));
            ClassReader cr = new ClassReader(is);
            ReadMethodArgNameClassVisitor classVisitor = new ReadMethodArgNameClassVisitor();
            cr.accept(classVisitor, 0);
            for(Entry<String, List<String>> entry : classVisitor.nameArgMap.entrySet()) {
                if(entry.getKey().equals(methodName)) {
                    for (String s : entry.getValue()) {
                        list.add(s);
                    }
                }
            }
            return list;
        }



    public static Object[] paramarray(List<String> paraNames, List<String> classNames, HttpServletRequest req, HttpServletResponse resp, Map params) {
        List<Object> list=new ArrayList<Object>();

        String paramValue = "";

        for(int i=0,length=paraNames.size();i<length;i++)
        {
            String className=classNames.get(i);

            if(className.equals("javax.servlet.http.HttpServletRequest"))
                list.add(req);
            else if(className.equals("javax.servlet.http.HttpServletResponse"))
                list.add(resp);
            else if(className.equals("javax.servlet.http.HttpSession")){
                list.add(req.getSession());
            }
            else {
                Iterator it1 = params.keySet().iterator();

                while (it1.hasNext()) {

                    String paramName = (String) it1.next();
                    paramValue = req.getParameter(paramName);

                    if (paraNames.get(i).toString().equals(paramName)) {

                        list.add(paramValue);
                    }
                }

            }

        }
        return list.toArray();
     }

    }


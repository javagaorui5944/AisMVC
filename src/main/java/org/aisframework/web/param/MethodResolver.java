package org.aisframework.web.param;


import org.aisframework.web.asm.ReadMethodArgNameClassVisitor;
import org.aisframework.web.structure.ModelMap;
import org.aisframework.web.utils.Config;
import org.aisframework.web.utils.ReflectUtils;
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


//        public static void main(String[] args) throws IOException {
//            String ss="cn.asens.test.sad.Sa";
//            ss="/D:/IDEA/AsMVC/target/AsMVC-1.0-SNAPSHOT/WEB-INF/classes/"+ss.replace(".","/");
//            InputStream is=new FileInputStream(new File(ss+".class"));
//            ClassReader cr = new ClassReader(is);
//        }

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
        //判断逻辑：1，req,resp 匹配 2，类型+名称匹配 3，名称匹配 4，置null 5，组合成类，只要有名字与类中的名字相同的，类就要实例化并注入
        //建立一个集合L，包括request，response，request.getParamters()
        //遍历方法中的类和名，如果是req，resp直接覆盖
        //如果类型+名称匹配，覆盖
        //如果名称匹配，修改类型，覆盖，类型修改失败，报错
        //如果不是基础类，且能找到这个类，且该类的属性与req中有相同的，有几个相同的，新建，赋值，否则为null，找不到，为null
        //为null
        //难点：1.request.getParamters()有没有类名+属性名，貌似全是字符串，2.我全部替换完成了，怎么放到可变参数方法中,Object数组
        public static Object[] makeArgs(List<String> paraNames, List<String> classNames, HttpServletRequest req, HttpServletResponse resp,ModelMap model) {
            List<Object> list=new ArrayList<Object>();
            List<String> reqNameList=new ArrayList<String>();
            Map<String,String> reqParaMap=new HashMap<String, String>();
            Enumeration<String> paras= req.getParameterNames();
            while (paras.hasMoreElements())
            {
                String name=paras.nextElement();
                reqNameList.add(name);
                reqParaMap.put(name,req.getParameter(name));
            }
            for(int i=0,length=paraNames.size();i<length;i++)
            {
                String paraName=paraNames.get(i);
                String className=classNames.get(i);
                if(className.equals("javax.servlet.http.HttpServletRequest"))
                    list.add(req);
                else if(className.equals("javax.servlet.http.HttpServletResponse"))
                    list.add(resp);
                else if(className.equals("cn.asens.structure.ModelMap"))
                    list.add(model);
                else if(className.equals("java.lang.String")&&reqNameList.contains(paraName))
                    list.add(reqParaMap.get(paraName));
                else if(reqNameList.contains(paraName)) {
                    if (className.equals("java.lang.Integer") || className.equals("int")) {
                        list.add(Integer.valueOf(reqParaMap.get(paraName)));
                    } else if (className.equals("java.lang.Boolean") || className.equals("boolean")) {
                        list.add(Boolean.valueOf(reqParaMap.get(paraName)));
                    }
                    //这个类能加载且有相同名字的属性
            }else if(ReflectUtils.classLoaded(className)&&ReflectUtils.hasField(className,reqParaMap)) {
                    Object o=ReflectUtils.getObjectByClassAndReq(className,reqParaMap);
                    list.add(o);
                }else {
                    list.add(null);
                }
            }
            return list.toArray();
        }
    }


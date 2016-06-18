package org.aisframework.web.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javafx.beans.binding.ObjectExpression;
import org.aisframework.web.annotation.Controller;
import org.aisframework.web.annotation.MapURL;
import org.aisframework.web.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaorui on 16/6/14.
 */
@Controller
public class test {

    @MapURL("get")
    public void get(String s1,String s2,HttpServletRequest request, HttpServletResponse response, HttpSession session){

        System.out.println("pram:"+session.getId()+"==="+s1+"=="+s2);


    }

    @MapURL("post")
    public void post(HttpServletResponse response){

        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map map = new HashMap();
        map.put("userid",1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid",1);
        pw.print(jsonObject);
    }

    @MapURL("showUser")
    public void showUser(String s1,String s2){

        System.out.println("pram:"+s1+"==="+s2);

    }

    @MapURL("foward")
    public String foward(){

        return "page/succ";

    }

    @MapURL("getUser")
    @ResponseBody
    public Object getUser(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userid",1);
        return jsonObject;

    }
}

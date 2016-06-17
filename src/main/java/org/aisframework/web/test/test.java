package org.aisframework.web.test;

import org.aisframework.web.annotation.Controller;
import org.aisframework.web.annotation.MapURL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public void post(int id){

        System.out.println("post");

    }

    @MapURL("showUser")
    public void showUser(String s1,String s2){

        System.out.println("pram:"+s1+"==="+s2);

    }
}

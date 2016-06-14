package org.aisframework.web.test;

import org.aisframework.web.annotation.Controller;
import org.aisframework.web.annotation.MapURL;

/**
 * Created by gaorui on 16/6/14.
 */
@Controller
public class test {

    @MapURL("get")
    public void get(){

        System.out.print("get");

    }

    @MapURL("post")
    public void post(){

        System.out.print("post");

    }
}

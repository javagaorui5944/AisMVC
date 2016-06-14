package org.aisframework.web.structure;

import java.lang.reflect.Method;

/**
 * Created by lenovo on 2016/5/16.
 */
public class MethodPro {
    private String name;
    private String url;
    private String urlStyle;
    private Method method;

    public MethodPro(Method method, String url, String urlStyle) {
        this.method = method;
        this.url = url;
        this.urlStyle = urlStyle;
        this.name=method.getName();
    }

    public MethodPro()
    {

    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlStyle() {
        return urlStyle;
    }

    public void setUrlStyle(String urlStyle) {
        this.urlStyle = urlStyle;
    }


}

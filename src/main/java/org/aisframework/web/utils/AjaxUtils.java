package org.aisframework.web.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gaorui on 16/7/1.
 */
public class AjaxUtils {
    public static boolean isAjaxRequest(HttpServletRequest request){

        String requestedWith = request.getHeader("X-Requested-With");
        Boolean isAjax = requestedWith != null ? requestedWith.equals("XMLHttpRequest"):false;

        return isAjax;
    }
}

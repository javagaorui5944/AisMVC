package org.aisframework.web.structure;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/5/22.
 */
public class ModelMap {
    public Map<String,Object> map;

    public ModelMap(Map<String,Object> map)
    {
        this.map=map;
    }
    public ModelMap()
    {
       map=new HashMap<String, Object>();
    }

    public void put(String s,Object o)
    {
        map.put(s,o);
    }

    public void get(String s)
    {
        map.get(s);
    }

    public Map<String, Object> getMap() {
        return map;
    }
}

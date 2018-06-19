package cn.edu.tj.wenda.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mao on 2017/5/18.
 */
public class ViewObject {
    //用于在velocity和controller之间传递数据
    private Map<String,Object> objs = new HashMap<String,Object>();

    public void set(String key,Object value){
        objs.put(key,value);
    }
    public Object get(String key){
        return objs.get(key);
    }
}

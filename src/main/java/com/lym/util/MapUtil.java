package com.lym.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Map构建工具类
 * Created by liuyanmin on 2019/9/29.
 */
public class MapUtil {

    private Map<String, Object> map;

    private MapUtil(){
        map = new HashMap<>();
    }

    public static MapUtil builder(){
        return new MapUtil();
    }

    public MapUtil put(String key, Object value){
        this.map.put(key, value);
        return this;
    }

    public Map<String, Object> build(){
        return this.map;
    }

}

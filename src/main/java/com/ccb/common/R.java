package com.ccb.common;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*所有返回服务端的数据都会封装为R */
@Data
public class R<T> {

    private Integer code; //1成功，0和其它数字为失败

    private String msg;

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<T>();
        r.data = object;
        r.code = 1;
        return r;
    }
    public static <T> R<T> success()
    {
        R<T> r = new R<T>();
        r.msg="suc";
        r.code = 1;
        return r;
    }

    public static <T> R<T> error(String msg) {
        R r = new R();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public R<T> add(String key, Object value) {//添加动态数据
        this.map.put(key, value);
        return this;
    }

    //增添一个R类针对列表的用法，若有影响或者多余可删
    public static <T>R<T> successw(List<T> list){
        R<List<T>> r=new R<>();
        r.data=list;
        r.code=1;
        return (R<T>) r;

    }

}

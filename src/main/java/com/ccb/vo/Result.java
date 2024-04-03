package com.ccb.vo;

import lombok.Data;

@Data
public class Result<T> {
    public int code;
    public String msg;
    public T data;

    public static  <T> Result success(){
        return new Result("suc",0,null);
    }

    public static  <T> Result success(T data){
        return new Result("suc",0,data);
    }

    private Result(String msg,int code,T data){
        this.msg=msg;
        this.code=code;
        this.data=data;
    }
}

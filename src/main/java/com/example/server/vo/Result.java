package com.example.server.vo;

public class Result <T>{//result to transfer data with a certain format
    public int code;
    public String msg;
    public T data;
    public static <T>Result success(){
        Result re=new Result(0,"success",null);
        return re;
    }
    public static <T>Result success(T data){
        Result re=new Result(0,"success",data);
        return re;

    }
    private Result(int code,String meg,T data){
    this.code=code;
    this.msg=meg;
    this.data=data;
    }
}

package com.spx.result;

public class Result {
    private String msg; // 返回给页面的信息
    private int code; // 返回给页面的状态码
    private Object data; // 返回给页面的数据
    private String token;
    private String username;

    public Result() {
    }

    public Result(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
    public Result(int code) {
        this.code = code;
    }
    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Result(String msg, int code, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static Result ok(){
        return new Result(1);
    }
    public static Result ok(String data){
        return new Result(1,data);
    }

    public static Result err(String msg){ return new Result(msg,0);}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

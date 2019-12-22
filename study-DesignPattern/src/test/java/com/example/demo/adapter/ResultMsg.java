package com.example.demo.adapter;

public class ResultMsg {
    private Integer code;
    private String msg;
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public void setFail(String msg, Object data){
        this.msg = msg;
        this.data = data;
        this.code = 500;
    }

    public void setSuccess(String msg,Object data){
        this.msg = msg;
        this.data = data;
        this.code = 200;
    }

    public void setFail(){
        this.code = 500;
    }

    public void setSuccess(){
        this.code = 200;
    }
}

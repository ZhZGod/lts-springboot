package com.example.lts.test.util;

import java.io.Serializable;

/**
 * 所有请求Service Responseonse的基类
 *
 * @author 黄祖荣
 * @version v2_2013_06_27
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String code;

    protected String msg;

    private String subCode;

    private String subMsg;

    private String body;

    private T data;

    private Boolean sessionTimeout;

    private static final Response SUCCESS = new Response();

    public boolean isSuccess() {
        return subMsg == null && subCode == null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSubCode() {
        return subCode;
    }

    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    public String getSubMsg() {
        return subMsg;
    }

    public void setSubMsg(String subMsg) {
        this.subMsg = subMsg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(Boolean sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public static Response required(String param) {
        Response sr = new Response();
        sr.subCode = String.format("%s is required.", param);
        sr.subMsg = String.format("%s is required.", param);
        return sr;
    }

    public static <T> Response<T> success() {
        return SUCCESS;
    }

    public static <T> Response<T> success(T data) {
        Response<T> sr = new Response<>();
        sr.data = data;
        return sr;
    }

    public static <T> Response<T> failed(String subCode, String subMsg) {
        Response<T> sr = new Response<>();
        sr.subCode = subCode;
        sr.subMsg = subMsg;
        return sr;
    }

    public static <T> Response<T> sessionTimeout() {
        Response<T> sr = new Response<>();
        sr.sessionTimeout = true;
        return sr;
    }
}
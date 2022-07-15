package com.king.id_maker.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月15日 01:41
 * @description:
 */
public class JsonModel {
    private Integer code;
    private String msg;
    private Object result;

    public JsonModel(Integer code, String msg, Object result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public JsonModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static JsonModel success(Object result) {
        return new JsonModel(1, "success", result);
    }

    public static JsonModel success() {
        return new JsonModel(1, "success");
    }

    public static JsonModel error(String msg) {
        return new JsonModel(0, msg);
    }

    public static JsonModel error(String msg, Object result) {
        return new JsonModel(0, msg, result);
    }

    @Override
    public String toString() {
        return "JsonModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}

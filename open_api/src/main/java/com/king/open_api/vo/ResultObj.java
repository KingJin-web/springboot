package com.king.open_api.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月27日 05:00
 */
@Data
@ApiModel(value = "返回响应类")
public class ResultObj implements Serializable {

    private static final long serialVersionUID = 5897449533099355749L;
    @ApiModelProperty(value = "解析接口状态")
    private Integer code;
    @ApiModelProperty(value = "解析提示文本")
    private String msg;
    @ApiModelProperty(value = "解析数据长度")
    private Integer count;
    @ApiModelProperty(value = "解析数据列表")
    private Object data;

    public ResultObj(Integer code, String msg, Integer count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public ResultObj() {
    }

    public static ResultObj success(String msg, Object data) {
        return new ResultObj(0, msg, 0, data);
    }

    public static ResultObj success(String msg,Integer count, Object data) {
        return new ResultObj(0, msg, count, data);
    }

    public static ResultObj success(Integer count, Object data) {
        return new ResultObj(0, "success", count, data);
    }

    public static ResultObj success(Object data) {
        return new ResultObj(0, "success", 0, data);
    }

    public static ResultObj success() {
        return new ResultObj(0, "success", 0, null);
    }

    public static ResultObj error(Integer code, String msg) {
        return new ResultObj(code, msg, 0, null);
    }

    public static ResultObj error(String msg) {
        return new ResultObj(1, msg, 0, null);
    }

    public static ResultObj error() {
        return new ResultObj(1, "error", 0, null);
    }

}
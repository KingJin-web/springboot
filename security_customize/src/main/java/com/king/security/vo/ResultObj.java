package com.king.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @program: mooc
 * @description:
 * @author: King
 * @create: 2021-10-03 12:06
 */
@Data
@ApiModel(value = "返回响应类")
public class ResultObj {
    @ApiModelProperty(value = "解析接口状态")
    private Integer code = 1;
    @ApiModelProperty(value = "解析提示文本")
    private String msg;
    @ApiModelProperty(value = "解析数据长度")
    private Integer count;
    @ApiModelProperty(value = "解析数据列表")
    private Object data;

    public ResultObj() {

    }

    public static ResultObj error() {
        return error(0, "操作失败");
    }

    public static ResultObj error(String msg) {
        return error(0, msg);
    }

    public static ResultObj error(int code, String msg) {
        ResultObj r = new ResultObj();
        r.code = code;
        r.msg = msg;
        return r;
    }

    public static ResultObj ok(ResultObj resultObj) {
        return resultObj;
    }
    public static ResultObj ok() {
        return ok("成功！");
    }
    public static ResultObj ok(Object o) {
        ResultObj r = new ResultObj();
        r.code = 1;
        r.msg = "成功！";
        r.data = o;
        return r;
    }
    public static ResultObj ok(String msg) {
        ResultObj r = new ResultObj();
        r.code = 1;
        r.msg = msg;
        return r;
    }


}

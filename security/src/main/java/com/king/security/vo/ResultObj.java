package com.king.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
}

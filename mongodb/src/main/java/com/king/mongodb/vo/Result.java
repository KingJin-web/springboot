package com.king.mongodb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-18 14:26
 */
@Data
@ApiModel(value = "返回参数类")
public class Result {
    @ApiModelProperty(value = "状态")
    protected boolean status;
    @ApiModelProperty(value = "返回信息")
    protected String message;
    @ApiModelProperty(value = "数据")
    protected Object data;
    @ApiModelProperty(value = "数据量")
    protected Integer count;
}

package com.king.other.short_link.vo;

import lombok.Data;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-09-18 14:26
 */
@Data
public class Result {
    protected boolean status;
    protected String message;
    protected Object data;
}

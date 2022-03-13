package com.king.security.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2022-03-13 15:11
 */
@Data
@Builder
public class UserLog {
    private Long uid;
    private String name;
    private String ip;
    private String adderss;
}

package com.king.bean;

import lombok.Builder;
import lombok.Data;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-08-21 00:07
 */
@Builder
@Data
public class Users implements Serializable {
    //自增唯一id
    private Long id;

    //datetime类型的时间
    private Timestamp timeDatetime;

    //timestamp类型的时间
    private Timestamp timeTimestamp;

    //long类型的时间
    private long timeLong;
}
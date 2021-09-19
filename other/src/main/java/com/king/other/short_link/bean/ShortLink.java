package com.king.other.short_link.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: springboot
 * @description: 短链接实体类
 * @author: King
 * @create: 2021-09-18 14:02
 */
@Data
@TableName(value = "short_link")//指定表名
public class ShortLink {
    @TableId(value = "id", type = IdType.AUTO)//指定自增策略
    private Integer id;
    //随机生成的字符
    private String shorts;
    //短链接
    private String shortLink;
    //长链接
    private String longLink;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //是否被删除，0表示未删除，1表示已经删除
    //@TableLogic  3.3.0,配置后可以忽略不配置
    private int flag;

    public void println() {
        System.out.println(this);
    }
}

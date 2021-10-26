package com.king.mybatis_xml.bean;



import lombok.Data;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

/**
 * @program:
 * @description: 用户实体类
 * @author:
 * @create:
 */
@Data


public class User {
    private static final long serialVersionUID = -7585862229833387698L;

    private Integer id;
    private String name;
    private String sex;
    private String pwd;
    private String email;
    private Integer age;
    private Long version;
    public void println() {
        System.out.println(this);
    }


}

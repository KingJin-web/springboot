package com.king.dao;

import com.king.bean.Users;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-08-21 16:33
 */
public class UsersDaoProvider {
    public String insertAll(Map map) {
        List<Users> list = (List<Users>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("insert into users");
        sb.append("(time_datetime, time_timestamp, time_long) ");
        sb.append("value ");
        MessageFormat mf = new MessageFormat("(#'{'list[{0}].timeDatetime},#'{'list[{0}].timeTimestamp},#'{'list[{0}].timeLong})");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        System.out.println(sb);
        return sb.toString();
    }
}

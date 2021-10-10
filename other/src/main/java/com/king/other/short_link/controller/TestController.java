package com.king.other.short_link.controller;

import com.king.other.short_link.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springboot
 * @description:
 * @author: King
 * @create: 2021-10-10 23:14
 */
@RestController
@Api(value = "操作接口", tags = "测试接口2")
public class TestController {
    @PostMapping(value = "/test.do")
    @ApiOperation(value = "测试接口多个参数", tags = "测试接口2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", dataType = "string", paramType = "query", example = "lihailin9073", required = true),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "string", paramType = "query", example = "jinpeng.qmail@qq.com", required = true),
    })
    public Result test(String name, String email) {
        Result result = new Result();
        Map<String, String> map = new HashMap<>();
        map.put("用户名", name);
        map.put("邮箱", email);
        result.setData(map);
        result.setMessage("这是一个测试接口");
        result.setStatus(true);
        return result;
    }
}

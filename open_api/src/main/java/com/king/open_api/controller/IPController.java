package com.king.open_api.controller;

import cn.hutool.http.HttpUtil;
import com.king.open_api.service.GetAddressFromIpService;
import com.king.open_api.util.IPUtils;
import com.king.open_api.vo.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月27日 04:53
 * @description:
 */
@RestController
@RequestMapping("/api/ip")
@Api(value = "ip", tags = "ip")
public class IPController {

    private final GetAddressFromIpService getAddressFromIpService;

    //构造注入GetAddressFromIpService
    @Autowired
    public IPController(GetAddressFromIpService getAddressFromIpService) {
        this.getAddressFromIpService = getAddressFromIpService;
    }


    @ApiOperation(value = "获取IP地址信息", notes = "获取IP地址信息")
    @GetMapping("/getIpInfo.do")
    public ResultObj getIpInfo(HttpServletRequest request) {
        try {
            String ip = IPUtils.getIPAddress(request);
            return ResultObj.success(getAddressFromIpService.getAddressFromIp(ip));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.error("获取IP地址信息失败");
        }
    }

}
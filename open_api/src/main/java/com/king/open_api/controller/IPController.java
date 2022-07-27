package com.king.open_api.controller;

import com.king.open_api.service.GetAddressFromIpService;
import com.king.open_api.vo.ResultObj;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/getIpInfo.do")
    public ResultObj getIpInfo(String ip) {
        try {
            return ResultObj.success(getAddressFromIpService.getAddressFromIp(ip));
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.error("获取IP地址信息失败");
        }
    }

}
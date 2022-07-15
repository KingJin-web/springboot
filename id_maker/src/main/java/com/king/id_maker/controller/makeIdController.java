package com.king.id_maker.controller;

import com.king.id_maker.service.IdMaker;
import com.king.id_maker.vo.JsonModel;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: King
 * @project: springboot
 * @date: 2022年07月15日 01:52
 * @description:
 */
@RestController
public class makeIdController {
    private IdMaker idMaker;

    @SneakyThrows
    @RequestMapping(value = "/getId", method = {RequestMethod.POST,RequestMethod.GET})
    public JsonModel getId(@RequestParam("type") String type) {
        String id = null;
        String msg = "生成成功";
        int code = 1;
        if (idMaker == null) {
            return new JsonModel(0, "服务器未开启...", null);
        }
        if ("DELAY".equals(type)) {
            id = idMaker.generateId(IdMaker.RemoveMethod.DELAY);
        } else if ("IMMEDIATELY".equals(type)) {
            id = idMaker.generateId(IdMaker.RemoveMethod.IMMEDIATELY);
        } else if ("NONE".equals(type)) {
            id = idMaker.generateId(IdMaker.RemoveMethod.NONE);
        } else {
            msg = "生成失败";
            code = 0;
        }
        return new JsonModel(code, msg, id);
    }

    @SneakyThrows
    @RequestMapping(value = "/openId", method = {RequestMethod.POST,RequestMethod.GET})
    public JsonModel open(@RequestParam("nodeName")String nodeName) {
        try {
            System.out.println(idMaker);
            if (idMaker != null){
                return new JsonModel(0, "服务已开启", null);
            }
            idMaker = new IdMaker("/NameService/IdGen",nodeName);
            idMaker.start();
            return new JsonModel(1, "开启服务", null);
        }catch (Exception e){
            return new JsonModel(0, e.getMessage(), null);
        }
    }

    @SneakyThrows
    @RequestMapping(value = "/closeId", method = {RequestMethod.POST,RequestMethod.GET})
    public JsonModel close() {
        if (idMaker == null) {
            return JsonModel.error("服务未开启");

        } else {
            idMaker.stop();
            idMaker = null;
            return JsonModel.success("服务已关闭");
        }
    }
}
// Language: java
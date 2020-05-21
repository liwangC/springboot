package com.liwang.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author : chenliwang
 * e-mail : liwangC@163.com
 * date   : 15:54 2020/4/22
 * desc   : websocket测试
 * version: 1.0
 */
@Controller
public class SocketController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/websocket/{tableId}")
    public String webSocket(@PathVariable String tableId, Model model){
        try{
            logger.info("跳转到websocket的页面上");
            //通过Model进行对象数据的传递
            model.addAttribute("clientId",tableId);
            return "socket";
        }
        catch (Exception e){
            logger.info("跳转到websocket的页面上发生异常，异常信息是："+e.getMessage());
            return "error";
        }
    }

}

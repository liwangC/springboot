package com.liwang.websocket.config;

import com.liwang.websocket.socket.MyWebSocketHandler;
import com.liwang.websocket.socket.MyWebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author : chenliwang
 * @e-mail : liwangC@163.com
 * @date : 11:54 2020/5/20
 * @desc : ${description}
 * @version: 1.0
 */

@Component
@EnableWebSocket
public class OtherWebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @Autowired
    private MyWebSocketInterceptor myWebSocketInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler, "/websocket")
                .setAllowedOrigins("*")
                .addInterceptors(myWebSocketInterceptor);
    }
}

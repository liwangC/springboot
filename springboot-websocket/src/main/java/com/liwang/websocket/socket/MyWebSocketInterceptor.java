package com.liwang.websocket.socket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : chenliwang
 * @e-mail : liwangC@163.com
 * @date : 16:12 2020/5/8
 * @desc : 自定义拦截器拦截WebSocket请求
 * @version: 1.0
 */
@Component
public class MyWebSocketInterceptor implements HandshakeInterceptor {

    /**
     * 前置拦截用来注册用户信息，绑定 WebSocketSession
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        System.out.println("前置拦截~~");
        if (!(request instanceof ServletServerHttpRequest)) {
            return true;
        }

        //HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        //String clientId = (String) servletRequest.getSession().getAttribute("clientId");
        //attributes.put("clientId", clientId);

        return true;

    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        System.out.println("后置拦截~~");
    }

}

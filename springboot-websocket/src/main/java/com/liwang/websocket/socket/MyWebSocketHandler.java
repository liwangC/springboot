package com.liwang.websocket.socket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : chenliwang
 * @e-mail : liwangC@163.com
 * @date : 11:56 2020/5/20
 * @desc : ${description}
 * @version: 1.0
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private static final Map<String, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();

    /**
     * 建立连接成功后回调
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        SESSIONS.put(session.getId(), session);
        System.out.println(String.format("成功建立连接~ tableId: %s", session.getId()));
    }

    /**
     * 接收客户端发送的Socket
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage) throws Exception {
        String msg = webSocketMessage.getPayload().toString();
        System.out.println(msg);
    }

    /**
     * 连接出错回调
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        System.out.println("连接出错");
        if (session.isOpen()) {
            session.close();
        }
    }

    /**
     * 连接关闭回调
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("连接已关闭,status:" + closeStatus);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}

package com.liwang.websocket.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author : chenliwang
 * e-mail : liwangC@163.com
 * date   : 15:30 2020/4/24
 * desc   : websocket处理类
 * version: 1.0
 */
//@Component
//@ServerEndpoint("/websocket/{clientId}")
public class MyWebSocket {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 在线人数
     */
    public static int onlineNumber = 0;

    /**
     * 以id为key，MyWebSocket为对象保存起来
     */
    private static Map<Integer, MyWebSocket> clients = new ConcurrentHashMap<>();

    /**
     * 会话
     */
    private Session session;

    /**
     * 用户名称
     */
    private int clientId;

    /**
     * 建立连接
     */
    @OnOpen
    public void onOpen(@PathParam(value = "clientId") int clientId, Session session) {
        onlineNumber++;
        this.clientId = clientId;
        this.session = session;
        logger.info("有新连接加入！当前在线人数" + onlineNumber);
    }

    @OnError
    public void onError(Throwable error) {
        logger.info("服务端发生了错误" + error.getMessage());
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        onlineNumber--;
        logger.info("有连接关闭！ 当前在线人数" + onlineNumber);
    }

    /**
     * 收到客户端的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {

    }


    public void sendMessageTo(String message, int ToTableId) throws IOException {
        for (MyWebSocket item : clients.values()) {
            if (item.clientId == ToTableId) {
                item.session.getAsyncRemote().sendText(message);
                break;
            }
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (MyWebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineNumber;
    }

}







































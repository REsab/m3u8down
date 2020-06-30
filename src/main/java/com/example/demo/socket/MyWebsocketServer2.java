package com.example.demo.socket;

import com.example.demo.domain.Message;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gjing
 **/
@ServerEndpoint("/test2")
@Component
@Slf4j
public class MyWebsocketServer2 {
    /**
     * 用于存放所有在线客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    private Gson gson = new Gson();

    @OnOpen
    public void onOpen(Session session) {
        log.info("有新的客户端上线: {}", session.getId());
        clients.put(session.getId(), session);
    }

    @OnClose
    public void onClose(Session session) {
        String sessionId = session.getId();
        log.info("有客户端离线: {}", sessionId);
        clients.remove(sessionId);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
        if (clients.get(session.getId()) != null) {
            clients.remove(session.getId());
        }
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("收到客户端发来的消息: {}", message);
        this.sendTo(gson.fromJson(message, Message.class));
    }

    /**
     * 发送消息
     *
     * @param message 消息对象
     */
    private void sendTo(Message message) {
        Session s = clients.get(message.getUserId());
        if (s != null) {
            try {
                s.getBasicRemote().sendText(message.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//————————————————
//        版权声明：本文为CSDN博主「阿靖哦」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/weixin_43835717/java/article/details/94149287
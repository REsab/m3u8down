package com.example.demo.socket;

import com.example.demo.observer.MsgBean;
import com.example.demo.observer.MsgObserver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gjing
 **/
@ServerEndpoint("/test")
@Component
@Slf4j
public class MyWebsocketServer {
    /**
     * 存放所有在线的客户端
     */
    private static Map<String, Session> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        log.info("有新的客户端连接了: {}", session.getId());
        //将新用户存入在线的组
        clients.put(session.getId(), session);
    }

    /**
     * 客户端关闭
     *
     * @param session session
     */
    @OnClose
    public void onClose(Session session) {
        log.info("有用户断开了, id为:{}", session.getId());
        //将掉线的用户移除在线的组里
        clients.remove(session.getId());
    }

    /**
     * 发生错误
     *
     * @param throwable e
     */
    @OnError
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * 收到客户端发来消息
     *
     * @param message 消息对象
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("服务端收到客户端发来的消息: {}", message);


        for (int i = 0; i < 33; i++) {


            MsgBean msgBean = new MsgBean();
            new MsgObserver(msgBean);


            msgBean.setMessage("sldfkdf     " + System.currentTimeMillis());
            this.sendAll(message + msgBean.getMessage());

            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 群发消息
     *
     * @param message 消息内容
     */
    private void sendAll(String message) {
        for (Map.Entry<String, Session> sessionEntry : clients.entrySet()) {
            sessionEntry.getValue().getAsyncRemote().sendText(message);
        }
    }
}
//————————————————
//        版权声明：本文为CSDN博主「阿靖哦」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/weixin_43835717/java/article/details/94066791
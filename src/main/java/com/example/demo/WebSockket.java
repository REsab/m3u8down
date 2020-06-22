//package com.example.demo;
//
//
//import lombok.extern.slf4j.Slf4j;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//
//
//@Slf4j
//@ServerEndpoint("/websocket")
//public class WebSockket {
//
//    @OnOpen
//    public void onOpen(Session session) throws IOException {
//        log.info("websocket  started ...");
//        session.getBasicRemote().sendText("welcome");
//
//    }
//
//    @OnClose
//    public void onClose() {
//        System.out.println("websocket closed");
//
//    }
//
//    @OnMessage
//    public void onMessage(String msg, Session session) throws IOException {
//
//
//        System.out.println("get msg ");
//        session.getBasicRemote().sendText("server get msg");
//
//    }
//
//}

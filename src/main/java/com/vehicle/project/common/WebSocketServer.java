package com.vehicle.project.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket 服务
 * @author xx
 */

@Slf4j
@Component
@ServerEndpoint("/webSocket/{webName}/{userName}")
public class WebSocketServer {

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private  Session session;

    private String webName = "";

    /**
     * 接收userName
     */
    private String userName = "";
    // 心跳检查 信息
    private static final String heartMsg = "heartCheck";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("webName") String webName, @PathParam("userName") String userName) {
        log.info("连接建立 模块：{}--用户名：{} 成功", webName, userName);
        this.session = session;
        this.userName = userName;
        this.webName = webName;
        //如果存在就先删除一个，防止重复推送消息
        for (WebSocketServer webSocket : webSocketSet) {
            if (webSocket.userName.equals(userName) && webSocket.webName.equals(webName)) {
                webSocketSet.remove(webSocket);
            }
        }
        webSocketSet.add(this);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        log.info("连接关闭 模块：{}---用户名：{} 成功", this.webName, this.userName);
        webSocketSet.remove(this);
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
//        log.info("收到来 {}-{} 的信息: {}", webName, userName, message);
        if (heartMsg.equals(message)) {
            try {
                sendInfo(message, webName, userName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                synchronized (item) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String socketMsg, @PathParam("webName") String webName, @PathParam("userName") String userName) throws IOException {
        // String message = JSONObject.toJSONString(socketMsg);
//        log.info("推送消息到 {}-{}，推送内容: {}", webName, userName, socketMsg);
        for (WebSocketServer item : webSocketSet) {
            try {
                synchronized (item) {
                    //这里可以设定只推送给这个sid的，为null则全部推送
                    if (userName == null) {
                        item.sendMessage(socketMsg);
                    } else if (item.webName.equals(webName) && item.userName.equals(userName)) {
                        item.sendMessage(socketMsg);
                    }
                }
            } catch (IOException ignored) {
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WebSocketServer that = (WebSocketServer) o;
        return Objects.equals(session, that.session) &&
                Objects.equals(userName, that.userName)
                && Objects.equals(webName, that.webName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, webName, userName);
    }


}

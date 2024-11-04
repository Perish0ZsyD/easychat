package com.easychat.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SocketServer
 * @Description 学习一下基本的socket编程，后面直接使用Netty框架
 * @Author Siyuan
 * @Date 2024/11/04/19:22
 * @Version 1.0
 */
public class SocketServer {
    public static void main(String[] args) {
        ServerSocket server = null;
        Map<String, Socket> CLENT_MAP = new HashMap<>();
        try {
            server = new ServerSocket(1024);
            System.out.println("服务器启动成功，等待客户端连接，端口号：" + 1024);
            while (true) {
                Socket socket = server.accept();
                String ip = socket.getInetAddress().getHostAddress();
                System.out.println("客户端IP：" + ip + "已连接到服务器" + "， 端口" + socket.getPort());
                String clientKey = ip + ":" + socket.getPort();
                CLENT_MAP.put(clientKey, socket); // 聊天池消息
                new Thread(() -> {
                    while (true) {
                        try (InputStream inputStream = socket.getInputStream();
                             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){
                            String readData = bufferedReader.readLine();
                            System.out.println("收到客户端消息：" + readData);

                            CLENT_MAP.forEach((k, v) -> { // 广播消息，给所有客户端发送消息（群聊）
                                try {
                                    OutputStream outputStream = v.getOutputStream();
                                    PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
                                    printWriter.println(socket.getPort() + "服务器已收到消息：" + readData);
                                    printWriter.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });

                        } catch (IOException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package com.easychat.test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @ClassName SocketClient
 * @Description
 * @Author Siyuan
 * @Date 2024/11/04/19:42
 * @Version 1.0
 */
public class SocketClient02 {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 1024);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            System.out.println("客户端发送消息");

            new Thread(() -> {
                while (true) {
                    Scanner scanner = new Scanner(System.in);
                    String message = scanner.nextLine();
                    try {
                        printWriter.println(message);
                        printWriter.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            new Thread(() -> {
                while (true) {
                    try {
                        String readData = bufferedReader.readLine();
                        System.out.println("收到服务器消息：" + readData);
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String string = input.readLine();
                    if (string.contains("msg=Hello")) {
                        System.out.println("Hello");
                    } else if (string.contains("msg=Any")) {
                        System.out.println("What");
                    } else if (string.contains("msg=Exit")) {
                        System.out.println("Server is going sleep. ByeBye");
                        server.close();
                    }
                    output.flush();
                }
            }
        }
    }
}
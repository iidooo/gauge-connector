package com.iidooo.gauge;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class TcpServer {
    private static final Logger logger = Logger.getLogger(TcpServer.class);

    public void run(int serverPort) {
        try {
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection connection = new Connection(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.fatal(e);
        }
    }
}

class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;

    public Connection(Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            String data = in.readUTF();
            // out.writeUTF(data);
            if (data.equals("ABC")) {
                out.writeUTF("<result><!-- code: 0 - error, 1 - success --><code>0</code><weight>111.33</weight><unit>kg</unit><errmsg></errmsg></result>");
            }
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {/* 关闭失败 */
            }
        }
    }

}

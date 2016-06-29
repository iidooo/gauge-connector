package com.iidooo.gauge;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.iidooo.gauge.action.GaugeDataController;
import com.iidooo.gauge.thread.SocketConnect;
import com.iidooo.gauge.util.MybatisUtil;
import com.iidooo.gauge.util.ValidateUtil;

public class MainClass {

    private static final Logger logger = Logger.getLogger(MainClass.class);

    public static void main(String[] args) {
        try {
            if (args.length <= 0) {
                System.out.println("The port params is required!");
                return;
            }

            String serverPort = args[0];
            if (!ValidateUtil.isNumber(serverPort)) {
                System.out.println("The port format is wrong!");
                return;
            }

            if (!MybatisUtil.load("mybatis/Configuration.xml")) {
                System.out.println("Database connection falied!");
                return;
            }

            GaugeDataController gaugeDataController = new GaugeDataController();
            ServerSocket listenSocket = new ServerSocket(Integer.parseInt(serverPort));
            while (true) {
                Socket clientSocket = listenSocket.accept();

//                byte[] buf = new byte[1024];
//                int len = 0;
//                InputStream input = clientSocket.getInputStream();
//                OutputStream output = clientSocket.getOutputStream();
//                String receivedData = "";
//                while ((len = input.read(buf)) != -1) {
//                    receivedData = new String(buf, 0, len);
//                    gaugeDataController.receiveGaugeData(receivedData);
//
//                    receivedData = "Reponse:" + receivedData;
//                    output.write(receivedData.getBytes());
//                }

                SocketConnect connection = new SocketConnect(clientSocket, gaugeDataController);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal(e);
        }
    }
}

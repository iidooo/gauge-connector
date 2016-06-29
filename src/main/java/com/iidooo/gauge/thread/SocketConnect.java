package com.iidooo.gauge.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.iidooo.gauge.action.GaugeDataController;

public class SocketConnect extends Thread {

    private static final Logger logger = Logger.getLogger(SocketConnect.class);
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
    private GaugeDataController gaugeDataController;

    public SocketConnect(Socket clientSocket, GaugeDataController gaugeDataController) {
        try {
            this.clientSocket = clientSocket;
            in = new DataInputStream(this.clientSocket.getInputStream());
            out = new DataOutputStream(this.clientSocket.getOutputStream());
            this.gaugeDataController = gaugeDataController;
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
            logger.fatal(e);
        }
    }

    public void run() {
        try {
            byte[] buf = new byte[1024];
            int len = 0;
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            String receivedData = "";
            String responseData = "";
            logger.info("run start");
            while ((len = input.read(buf)) != -1) {
                receivedData = new String(buf, 0, len);
                responseData = "Reponse success: data is " + receivedData;

                gaugeDataController.receiveGaugeData(receivedData);

                output.write(responseData.getBytes());

                buf = new byte[1024];
                len = 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal(e);
        } finally {
            try {
                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.fatal(e);
            }
        }
    }
}

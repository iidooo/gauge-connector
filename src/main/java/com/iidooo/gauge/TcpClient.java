package com.iidooo.gauge;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class TcpClient {
    public static void main(String[] args) {
        String[] str = { "192.168.31.103", "8006" };
        // client(str);
        try {
            onBoReadNoTest();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void client(String[] str) throws IOException {
        Socket s = null;
        InputStream ips = null;
        OutputStream ops = null;
        BufferedReader brKey = null;
        DataOutputStream dos = null;
        BufferedReader brNet = null;
        try {
            // Socket s=new Socket(InetAddress.getByName("192.168.0.213"),8001);
            if (str.length < 2) {
                System.out.println("Usage:java TcpClient ServerIP ServerPort");
                return;
            }
            // 建立Socket
            s = new Socket(InetAddress.getByName(str[0]), Integer.parseInt(str[1]));
            ips = s.getInputStream();
            ops = s.getOutputStream();

            dos = new DataOutputStream(ops);
            brNet = new BufferedReader(new InputStreamReader(ips));

            while (true) {
                brKey = new BufferedReader(new InputStreamReader(System.in));// 键盘输入
                String strWord = brKey.readLine();
                dos.writeBytes(strWord + System.getProperty("line.separator"));
                if (strWord.equalsIgnoreCase("quit"))
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dos.close();
            brNet.close();
            brKey.close();
            s.close();
        }
    }

    // 测试TCP通讯
    public static void onBoReadNoTest() throws Exception {
        Socket soc = null;
        InputStreamReader isr = null;// 定义一个可读输入流
        String data = "";
        BufferedReader bf = null;// 定义一个BufferedReader类型的读内容的引用
        InetAddress addr = InetAddress.getByName("192.168.31.103");
        int serverPort = 8006;
        if (addr.isReachable(5000)) {
            System.out.println("SUCCESS - ping " + addr + " with no interface specified");
            try {
                soc = new Socket(addr, serverPort);
                System.out.println("Socket Success!");
                DataInputStream in = new DataInputStream(soc.getInputStream());
                DataOutputStream out = new DataOutputStream(soc.getOutputStream());
                //out.writeUTF({"productCode:00100100123456,temperature:800,atmos:70,particulate:30"});
                // BufferedReader brin = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                System.out.println("DataInputStream Success!");
                data = in.readUTF();
                // System.out.println("接收到的数据:" + brin.readLine());
                // isr = new InputStreamReader(soc.getInputStream());// 创建一个来自套接字soc的可读输入流
                // bf = new BufferedReader(isr);// 把soc的可读输入流作为参数创建一个BufferedReader
                // data = bf.readLine();// 以每行为单位读取从客户端发来的数据
                System.out.println("Received Data:" + data);
                //DOM(data);
                // in.close();
            } catch (UnknownHostException e) {
                System.out.println("Socket Error:" + e.getMessage());
            } catch (EOFException e) {
                System.out.println("EOF:" + e.getMessage());
            } catch (IOException e) {
                System.out.println("IO:" + e.getMessage());
            } finally {
                if (soc != null)
                    try {
                        soc.close();
                    } catch (IOException e) {/* close failed */
                    }
            }
        } else {
            System.out.println("FAILURE - ping " + addr + " with no interface specified");
        }

    }

    // DOM解析方法
    public static void DOM(String data) {
        long lasting = System.currentTimeMillis();

        try {
            // File f = new File("F:/xmltest.xml");
            // FileInputStream fis=new FileInputStream("data.xml");
            // BufferedInputStream bis=new BufferedInputStream(fis);
            // DataInputStream dis=new DataInputStream(bis);

            byte[] b = data.getBytes();
            InputStream inp = new ByteArrayInputStream(b);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inp);
            NodeList nl = doc.getElementsByTagName("result");
            for (int i = 0; i < nl.getLength(); i++) {
                System.out.println("||code:  |" + doc.getElementsByTagName("code").item(i).getFirstChild().getNodeValue());
                System.out.println("||weight:  |" + doc.getElementsByTagName("weight").item(i).getFirstChild().getNodeValue());
                System.out.println("||unit:  |" + doc.getElementsByTagName("unit").item(i).getFirstChild().getNodeValue());
                System.out.println("||errmsg:  |" + doc.getElementsByTagName("errmsg").item(i).getFirstChild().getNodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("DOM RUNTIME：" + (System.currentTimeMillis() - lasting) + " MS");
    }

}

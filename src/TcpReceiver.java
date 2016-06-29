import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TcpReceiver {

    public static void main(String[] args) {
        String[] str = { "172.30.5.58", "8080" };
        try {
            client(str);
        } catch (IOException e) {
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

}

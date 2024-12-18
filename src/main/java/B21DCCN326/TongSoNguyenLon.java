package B21DCCN326;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TongSoNguyenLon {
    public static void main(String[] args) {
        String studentCode = "B21DCCN326";
        String qCode = "cs9bQFVa";
        String serverAddress = "203.162.10.109";  // Địa chỉ IP của server
        int port = 2207  ;  // Cổng kết nối
        String message = studentCode + ";" + qCode;

        try (DatagramSocket socket = new DatagramSocket()){
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), port);
            socket.send(out);
            System.out.println(message);

            byte []buffer = new byte[1024];
            DatagramPacket in = new DatagramPacket(buffer, buffer.length);
            socket.receive(in);

            String response = new String(in.getData());
            System.out.println(response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

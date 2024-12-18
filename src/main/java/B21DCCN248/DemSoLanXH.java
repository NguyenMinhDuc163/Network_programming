package B21DCCN248;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

public class DemSoLanXH {
    public static void main(String[] args) {
        String studentCode = "B21DCCN248";
        String qCode = "0sKr3loP";
        String serverAddress = "203.162.10.109";  // Địa chỉ IP của server
        int port = 2208 ;  // Cổng kết nối
        String message = ";" + studentCode + ";" + qCode;

        try (DatagramSocket socket = new DatagramSocket()){
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), port);
            socket.send(out);
            System.out.println(message);

            byte[] buffer = new byte[1024];
            DatagramPacket in = new DatagramPacket(buffer, buffer.length);
            socket.receive(in);
            String response = new String(buffer, 0, in.getLength());
            System.out.println(response);
            String []s = response.split(";");

            LinkedHashMap<Character, Integer> mp = new LinkedHashMap<>();
            for(char x: s[1].toCharArray()){
                mp.put(x, mp.getOrDefault(x, 0) + 1);
            }
            String res = s[0] + ";";
            for(var x: mp.entrySet()){
                res += x.getValue() + "" + x.getKey();
            }

            out = new DatagramPacket(res.getBytes(), res.length(), InetAddress.getByName(serverAddress), port);
            socket.send(out);
            System.out.println(res);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

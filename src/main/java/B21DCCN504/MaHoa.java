package B21DCCN504;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MaHoa {
    public static void main(String[] args) {
        String ipAddress =  "203.162.10.109";
        int port = 2207 ;
        String message = ";B21DCCN504;D16ZYxt1";

        try (DatagramSocket socket = new DatagramSocket()){
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(ipAddress), port);
            socket.send(out);
            System.out.println("da gui " + message);

            byte [] buffer = new byte[1024];
            DatagramPacket in = new DatagramPacket(buffer, buffer.length);
            socket.receive(in);
            String response = new String(buffer, 0, in.getLength());
            System.out.println(response);

            String []s = response.split(";");
            String res = s[0] + ";";
            int des = Integer.parseInt(s[2]);
            for(char x: s[1].toCharArray()){
                char base = Character.isUpperCase(x) ? 'A' : 'a';
                x = (char) ((x - base + des) % 26 + base);
                res+=x;
            }

            out = new DatagramPacket(res.getBytes(), res.length(), InetAddress.getByName(ipAddress), port);
            socket.send(out);
            System.out.println(res);
        }catch (Exception e){

        }
    }
}

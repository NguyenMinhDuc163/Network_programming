package lam_lai_lan2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class SoNgtoNhoHonN {
    public static boolean inPrime(int n){
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n % i == 0)
                return false;
        }
        return n > 1;
    }

    public static void main(String[] args) {
        String ip = "203.162.10.109";
        int port = 2207;
        String message = ";B21DCCN249;nC4swdXD";
        try (DatagramSocket socket = new DatagramSocket()){
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(ip), port);
            socket.send(out);
            System.out.println(" da gui " + message);

            byte[] data = new byte[1024];
            DatagramPacket in = new DatagramPacket(data, data.length);
            socket.receive(in);

            String response = new String(in.getData(), 0, in.getLength());
            System.out.println(response);

            String s[] = response.split(";");
            int n = Integer.parseInt(s[1]);
            int cnt = 0;
            String res = s[0] + ";";
            for(int i = 0; i <= 1000000; i++){
                if(inPrime(i)){
                    res += i + ",";
                    cnt++;
                }
                if(cnt == n) break;
            }
            res = res.substring(0, res.length() - 1);
            out = new DatagramPacket(res.getBytes(), res.length(), InetAddress.getByName(ip), port);
            socket.send(out);
            System.out.println(" da gui " + res);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

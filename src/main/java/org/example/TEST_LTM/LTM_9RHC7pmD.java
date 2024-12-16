package org.example.TEST_LTM;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class LTM_9RHC7pmD {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2207;
        String studentCode = "B21DCCN249";
        String qCode = "9RHC7pmD";
        String message = ";" + studentCode + ";" + qCode;
        try(DatagramSocket socket = new DatagramSocket()

        ) {
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(),InetAddress.getByName(serverAddress), port);
            socket.send(out);
            System.out.println("da gui message" + message);

            byte []data = new byte[1024];
            DatagramPacket in = new DatagramPacket(data, data.length);
            socket.receive(in);
            String []tmp = new String(in.getData()).trim().split(";");
            String []a = tmp[1].split(",");

            System.out.println("data " + a);
            int max = -10000000;
            int min = 10000000;

            for(String x: a){
                int n = Integer.parseInt(x);
                max = Math.max(n, max);
                min = Math.min(n, min);
            }

            String ans = "requestId;" + max + "," + min;
            System.out.println("ket qua " + ans);

            DatagramPacket res = new DatagramPacket(ans.getBytes(), ans.length(), InetAddress.getByName(serverAddress), port);
            socket.send(res);

            System.out.println("da gui thanh cong");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}

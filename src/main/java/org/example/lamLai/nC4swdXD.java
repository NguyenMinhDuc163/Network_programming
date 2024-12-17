package org.example.lamLai;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class nC4swdXD {

  public static boolean isPrime(int n){
       for(int i = 2; i <= Math.sqrt(n); i++){
           if(n % i == 0)
               return false;
       }
       return n > 1;
   }

    public static void main(String[] args) {
        String ipAdress = "203.162.10.109";
        int port = 2207;
        String message = ";B21DCCN249;nC4swdXD";
        try (DatagramSocket socket = new DatagramSocket()){
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(ipAdress), port);
            socket.send(out);
            System.out.println("da gui" + message);

            byte []data = new byte[1024];
            DatagramPacket in = new DatagramPacket(data, data.length);
            socket.receive(in);
            String response = new String(in.getData(), 0, in.getLength());
            System.out.println(response);
            String []s = response.split(";");
            int n = Integer.parseInt(s[1]);

            String res = s[0] + ";";
            int cnt = 0;
            for(int i = 0 ; i < 10000000; i++){
                if(isPrime(i)){
                    res += i + ",";
                    cnt++;
                }
                if(cnt == n) break;
            }
            res = res.substring(0, res.length() - 1);
            out = new DatagramPacket(res.getBytes(), res.length(), InetAddress.getByName(ipAdress),port);
            socket.send(out);
            System.out.println(res);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

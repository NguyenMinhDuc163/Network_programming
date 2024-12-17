package org.example.lamLai;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;

public class exam_6LE6pI3Y {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2208;
        String message = ";B21DCCN249;6LE6pI3Y";
        try (DatagramSocket socket = new DatagramSocket()){
            DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.length(),
                    InetAddress.getByName(serverAddress), port);
            socket.send(datagramPacket);
            System.out.println("da gui " + message);

            byte []data = new byte[1024];
            DatagramPacket in = new DatagramPacket(data, data.length);
            socket.receive(in);
            String response = new String(in.getData(), 0, in.getLength());
            System.out.println(response);
            String []s = response.split(";");
            String requestId = s[0];
            String dataString = s[1];
            HashMap<Character, Integer> mp = new HashMap<>();
            for(char x : dataString.toCharArray()){
                mp.put(x, mp.getOrDefault(x, 0) + 1);
            }
            int maxVal = Integer.MIN_VALUE;
            char value = '0';
            for(var entry : mp.entrySet()){
                if(entry.getValue() > maxVal){
                    maxVal = entry.getValue();
                    value = entry.getKey();
                }
            }

            char firtIndx =  '0';
            for(char x: dataString.toCharArray()){
                if(mp.get(x) == maxVal){
                    firtIndx = x;
                    break;
                }
            }

            System.out.println("firtIndx " + firtIndx);
            String res = requestId + ";" + value + ":";
            for(int i = 0 ; i < dataString.length(); i++) {
                if(dataString.charAt(i) == firtIndx){
                    res += (i + 1) + ",";
                }
            }
            datagramPacket = new  DatagramPacket(res.getBytes(), res.length(),
                    InetAddress.getByName(serverAddress), port);
            socket.send(datagramPacket);
            System.out.println("res" + res);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2206;
        String studentId =  "B21DCCN249";
        String qCode  = "x9oOW5M4";
        String message = studentId  +  ";" + qCode;

        try (DatagramSocket socket = new DatagramSocket()){
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), port);
            socket.send(out);
            System.out.println("da gui" + message);

            byte []data = new byte[1024];
            DatagramPacket in = new DatagramPacket(data, data.length);
            socket.receive(in);

            String []response = new String(in.getData()).trim().split(";")[1].split(",");
            int max = Integer.MIN_VALUE;
            int min =  Integer.MAX_VALUE;
            for(String x : response){
                max = Math.max(max, Integer.parseInt(x));
                min = Math.min(min, Integer.parseInt(x));
            }
            String res = "requestId;" + max + "," + min;

            out = new DatagramPacket(res.getBytes(), res.length(), InetAddress.getByName(serverAddress), port);
            socket.send(out);
            System.out.println("da gui " + res);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

package org.example.lamLai;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class exam_1zP78Kmv {
    public static void main(String[] args) {
        String ipAddress =  "203.162.10.109";
        int port = 2206 ;
        String message = "B21DCCN249;1zP78Kmv";

        try (Socket socket = new Socket(ipAddress, port)){
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            output.write(message.getBytes());

            byte []buffer = new byte[1024];
            int data = input.read(buffer);
            String response = new String(buffer, 0, data);
            System.out.println(response);

            List<Integer> list = Arrays.stream(response.split(",")).map(Integer::parseInt).sorted().toList();
            int min = Integer.MAX_VALUE;
            int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
            for(int i = list.size() - 1; i >= 1; i--){
                if(list.get(i) - list.get(i - 1) < min){
                    min = list.get(i) - list.get(i - 1);
                    first = list.get(i);
                    second = list.get(i - 1);
                }
            }

            String res = min + "," + second + "," + first;
            output.write(res.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

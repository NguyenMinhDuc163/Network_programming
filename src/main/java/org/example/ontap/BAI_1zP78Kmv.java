package org.example.ontap;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collectors;

public class BAI_1zP78Kmv {
    public static void main(String[] args) {
        String studentCode = "B21DCCN249";
        String qCode = "1zP78Kmv";
        String serverAddress = "203.162.10.109";  // Địa chỉ IP của server
        int port = 2206;  // Cổng kết nối
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(serverAddress, port)) {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            outputStream.write(message.getBytes());
            outputStream.flush();
            System.out.println("da gui du lieu");

            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String response = new String(buffer, 0, bytesRead);

            List<Integer> arr = Arrays.stream(response.split(","))
                    .map(Integer::parseInt)
                    .sorted()
                    .toList();

            int ans = Integer.MAX_VALUE;
            int first = 0, second = 0;
            for(int i = arr.size() - 1; i >= 1; i--){
                if( arr.get(i) - arr.get(i - 1) < ans){
                    ans = arr.get(i) - arr.get(i - 1);
                    first = arr.get(i);
                    second = arr.get(i - 1);
                }
            }
            String res = ans + "," + second + "," + first;
            outputStream.write(res.getBytes());
            System.out.println("da gui " + res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

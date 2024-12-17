package org.example.lamLai;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Qhc67GrK {
    public static void main(String[] args) {
        String ipAddress =  "203.162.10.109";
        int port = 2208 ;
        String message = "B21DCCN249;Qhc67GrK";

        try (Socket socket = new Socket(ipAddress, port)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            writer.write(message);
            writer.newLine();
            writer.flush();
            System.out.println(" da gui " + message);

            String response = reader.readLine();
            System.out.println(response);

            LinkedHashSet<Character> se = new LinkedHashSet<>();

            for(char x: response.toCharArray()){
                if(Character.isAlphabetic(x)){
                    se.add(x);
                }
            }

            String res = se.stream().map(String::valueOf).collect(Collectors.joining());
            writer.write(res);
            writer.flush();
            System.out.println(res);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

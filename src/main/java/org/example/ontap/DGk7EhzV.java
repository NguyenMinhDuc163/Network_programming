package org.example.ontap;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class DGk7EhzV {

    public static void main(String[] args) {
        String studentCode = "B21DCCN249";
        String qCode = "DGk7EhzV";
        String serverAddress = "203.162.10.109";
        int port = 2207;
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(serverAddress,port)){
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            output.writeUTF(message);
            output.flush();

            int n = input.readInt();
            int []cnt = new int[7];
            for(int i = 0 ; i < n; i++){
                cnt[input.readInt()]++;
            }
            for(int i = 1; i <= 6 ;i ++){
                float tmp = cnt[i] / (float)n;
                output.writeFloat(tmp);
                output.flush();
                System.out.println(tmp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

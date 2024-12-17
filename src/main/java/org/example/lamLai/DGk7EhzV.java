package org.example.lamLai;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Arrays;

public class DGk7EhzV {
    public static void main(String[] args) {
        String ipAddress =  "203.162.10.109";
        int port = 2207;
        String message = "B21DCCN249;DGk7EhzV";

        try (Socket socket = new Socket(ipAddress, port)){
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(message);
            out.flush();
            System.out.println("da gui " + message);

            int n = in.readInt();
            int []cnt = new int[100];
            for(int i = 0; i < n; i++ ){
                cnt[in.readInt()]++;
            }
            for(int i = 1; i <= 6; i++){
                out.writeFloat(cnt[i] / (float)n);
                out.flush();
                System.out.print(cnt[i] / (float)n + " ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package org.example.TEST_LTM;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

class r3r8nBKI{
    public static void main(String[] args) {
        String severAddress = "203.162.10.109";
        int port = 2207;
        String msv = "B21DCCN249";
        String qCode = "r3r8nBKI";
        String message = msv + ";" + qCode;
        try(Socket socket = new Socket(severAddress, port)){
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            outputStream.writeUTF(message);
            outputStream.flush();
            System.out.println("da  gui message" +  message);

            int a = inputStream.readInt();
            int b = inputStream.readInt();
            System.out.println("Nhan data tu server = a" + a + "b" + b );

            int sum = a + b;
            outputStream.writeInt(sum);
            outputStream.flush();
            System.out.println("da gui sum" + sum);

            int mul = a * b;
            outputStream.writeInt(mul);
            System.out.println("da gui mul" + mul);
            outputStream.flush();

            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
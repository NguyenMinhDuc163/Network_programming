package org.example.ontap;

import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class Qhc67GrK {
    public static final String serverAddress = "203.162.10.109";
    public static final int port = 2208;
    public static final String studentCode = "B21DCCN249";
    public static final String qCode = "Qhc67GrK";
    public static final String message = studentCode + ";" + qCode;

    public static String logicProblem(String data){

        LinkedHashSet<Character> se = new LinkedHashSet<>();
        for(char x: data.toCharArray()){
            if(Character.isAlphabetic(x)){
                se.add(x);
            }
        }
        return se.stream().map(String::valueOf)
                .collect(Collectors.joining());
    }

    public static void inputStringFunc(){
        try (Socket socket = new Socket(serverAddress, port)){
            socket.setSoTimeout(5000);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes());
            outputStream.write('\n');
            outputStream.flush();
            System.out.println("da gui " + message);

            InputStream inputStream = socket.getInputStream();
            StringBuilder sb = new StringBuilder();
            int ch;
            while((ch = inputStream.read()) != -1){
                sb.append((char)ch);
            }
            String response = sb.toString().trim();
            System.out.println("da nhan " + response);
            String ans = logicProblem(response);
            outputStream.write(ans.getBytes());
            outputStream.write('\n');
            outputStream.flush();

            System.out.println(ans);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void bufferedWriterFunc(){
        try (Socket socket = new Socket(serverAddress, port)){
            socket.setSoTimeout(5000);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            System.out.println("da gui " + message);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = bufferedReader.readLine();
            System.out.println("da nhan " + response);


            String ans = logicProblem(response);
            bufferedWriter.write(ans);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            System.out.println("gui" + response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void dataOutputStreamFunc(){
        try (Socket socket = new Socket(serverAddress, port)){
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            outputStream.writeUTF(message);
            outputStream.flush();
            System.out.println("da gui " + message);

            String response = inputStream.readUTF();

            String ans = logicProblem(response);
            System.out.println("da nhan " + response);
            outputStream.writeUTF(ans);
            outputStream.flush();
        }catch (Exception e){

        }
    }
    public static void main(String[] args) {
//        inputStringFunc();
        bufferedWriterFunc();
//        dataOutputStreamFunc();
    }
}

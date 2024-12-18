package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class StudentUDP {
    public static void main(String[] args) {
        String ipAdress = "203.162.10.109";
        int port = 2209;
        String message = ";B21DCCN260;1FILoRDJ";

        try(DatagramSocket socket = new DatagramSocket()){
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(ipAdress), port);
            socket.send(out);
            System.out.println(message);

            byte[] buffer = new byte[1024];
            DatagramPacket in = new DatagramPacket(buffer, buffer.length);
            socket.receive(in);

            String qcode = new String(buffer, 0, 8);
            System.out.println(qcode);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer, 8, in.getLength() - 8);
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);

            Student student = (Student) inputStream.readObject();
            System.out.println(student);

            String []s = student.getName().split("\\s+");
            String name = "";
            for(String x: s){
                name += x.substring(0, 1).toUpperCase() + x.substring(1).toLowerCase() + " ";
            }
            student.setName(name.trim());


            String email = s[s.length - 1].toLowerCase();

            for(int i = 0 ; i < s.length - 1; i++){
                email += s[i].toLowerCase().charAt(0);
            }
            email += "@ptit.edu.vn";
            student.setEmail(email);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(student);

            byte [] studentByte = byteArrayOutputStream.toByteArray();
            byte [] request = new byte[8 + studentByte.length];

            System.arraycopy(qcode.getBytes(), 0, request, 0, 8);
            System.arraycopy(studentByte, 0, request, 8, studentByte.length);

            out = new DatagramPacket(request, request.length, InetAddress.getByName(ipAdress), port);
            socket.send(out);

            System.out.println(student);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package UDP;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    public static String reverseName(String name){
        String []s = name.split("\\s+");
        String res =  s[s.length - 1] + " ";
        for(int i = 1; i < s.length - 1; i ++){
            res += s[i] + " ";
        }
        return res + s[0];
    }

    public  static int reverseQuantity(int quantity){
        StringBuilder s = new StringBuilder(String.valueOf(quantity));
        return Integer.parseInt(s.reverse().toString());
    }
    public static void main(String[] args) {
        String ipAdress = "203.162.10.109";
        int port = 2209;
        String message = ";B21DCCN249;pU29UaGj";
        try(DatagramSocket socket = new DatagramSocket()){
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(ipAdress), port);
            socket.send(out);
            System.out.println("da gui" + out);

            byte []data = new byte[1024];
            DatagramPacket in = new DatagramPacket(data, data.length);
            socket.receive(in);

            String code = new String(in.getData(), 0, 8);
            System.out.println("code " + code);

            ByteArrayInputStream dataStream = new ByteArrayInputStream(in.getData(), 8, in.getLength() - 8);
            ObjectInputStream input = new ObjectInputStream(dataStream);
            Product product = (Product) input.readObject();
            System.out.println(product);

            product.setName(reverseName(product.getName()));
            product.setQuantity(reverseQuantity(product.getQuantity()));

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(product);
            byte []productByte = outputStream.toByteArray();

            byte [] request = new byte[8 + productByte.length];
            System.arraycopy(code.getBytes(), 0, request, 0, 8);
            System.arraycopy(productByte, 0, request, 8, productByte.length);

            out = new DatagramPacket(request, request.length, InetAddress.getByName(ipAdress), port);
            socket.send(out);
            System.out.println(product);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

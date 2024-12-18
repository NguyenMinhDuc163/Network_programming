package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ProductUDP {
    public static void main(String[] args) {
        String ip = "203.162.10.109";
        int port = 2209;
        String message = ";B21DCCN249;pU29UaGj";

        try (DatagramSocket socket = new DatagramSocket()){
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(ip), port);
            socket.send(out);
            System.out.println(message);

            byte[] buffer = new byte[1024];
            DatagramPacket in = new DatagramPacket(buffer, buffer.length);
            socket.receive(in);
            String code = new String(in.getData(), 0, 8);
            System.out.println(code);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(in.getData(), 8, in.getLength());
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
            Product product = (Product) inputStream.readObject();
            System.out.println(product);

            String []s = product.getName().split("\\s+");
            String name = s[s.length - 1] + " ";
            for(int i = 1; i < s.length - 1; i ++ ){
                name += s[i] + " ";
            }
            name += s[0];
            product.setName(name);

            int quantity = Integer.parseInt(new StringBuilder(String.valueOf(product.getQuantity())).reverse().toString());
            product.setQuantity(quantity);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(product);

            byte []byteProduct = byteArrayOutputStream.toByteArray();
            byte [] request = new byte[8 + byteProduct.length];

            System.arraycopy(code.getBytes(), 0, request, 0, 8);
            System.arraycopy(byteProduct, 0, request, 8, byteProduct.length);

            out = new DatagramPacket(request, request.length, InetAddress.getByName(ip), port);
            socket.send(out);
            System.out.println(product);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

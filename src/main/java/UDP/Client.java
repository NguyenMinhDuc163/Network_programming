package UDP;


import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    // Lớp Product


    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2209;
        String studentCode = "B21DCCN249";
        String qCode = "pU29UaGj";
        String message = ";" + studentCode + ";" + qCode;  // Chuỗi gửi đến server

        try (DatagramSocket socket = new DatagramSocket()) {
            // Gửi thông điệp đến server
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), port);
            socket.send(out);
            System.out.println("Đã gửi message: " + message);

            // Nhận phản hồi từ server (requestId và đối tượng Product)
            byte[] data = new byte[1024];
            DatagramPacket in = new DatagramPacket(data, data.length);
            socket.receive(in);

            // Lấy chuỗi requestId
            String requestId = new String(in.getData(), 0, 8).trim();  // 08 byte đầu chứa chuỗi requestId

            // Đọc đối tượng Product từ phần còn lại của packet
            ByteArrayInputStream byteStream = new ByteArrayInputStream(in.getData(), 8, in.getLength() - 8);
            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
            Product product = (Product) objectStream.readObject();
            System.out.println("Đã nhận từ server: " + product);

            // Sửa tên và số lượng
            product.setName(reverseName(product.getName()));
            product.setQuantity(reverseQuantity(product.getQuantity()));

            // Gửi lại thông điệp với đối tượng Product đã sửa
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);
            objectOut.writeObject(product);
            byte[] productBytes = byteOut.toByteArray();

            byte[] responseData = new byte[8 + productBytes.length];
            // Gắn requestId vào đầu chuỗi responseData
            System.arraycopy(requestId.getBytes(), 0, responseData, 0, 8);
            System.arraycopy(productBytes, 0, responseData, 8, productBytes.length);

            DatagramPacket res = new DatagramPacket(responseData, responseData.length, InetAddress.getByName(serverAddress), port);
            socket.send(res);
            System.out.println("Đã gửi kết quả: " + product);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Hàm đảo ngược tên sản phẩm: Đảo vị trí từ đầu tiên và cuối cùng
    public static String reverseName(String name) {
        String[] words = name.split(" ");
        if (words.length > 1) {
            String temp = words[0];
            words[0] = words[words.length - 1];
            words[words.length - 1] = temp;
        }
        return String.join(" ", words);
    }

    // Hàm đảo ngược số lượng (từ dạng số sang dạng đảo ngược)
    public static int reverseQuantity(int quantity) {
        String quantityStr = Integer.toString(quantity);
        StringBuilder reversed = new StringBuilder(quantityStr);
        reversed.reverse();
        return Integer.parseInt(reversed.toString());
    }
}

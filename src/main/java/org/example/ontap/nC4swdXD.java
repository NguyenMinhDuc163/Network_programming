package org.example.ontap;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class nC4swdXD {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2207;
        String studentCode = "B21DCCN249";
        String qCode = "nC4swdXD";
        String message = ";" + studentCode + ";" + qCode;  // Chuỗi gửi đến server

        try (DatagramSocket socket = new DatagramSocket()) {
            // Gửi thông điệp đến server
            DatagramPacket out = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), port);
            socket.send(out);
            System.out.println("Đã gửi message: " + message);

            // Nhận phản hồi từ server
            byte[] data = new byte[1024];
            DatagramPacket in = new DatagramPacket(data, data.length);
            socket.receive(in);
            String receivedMessage = new String(in.getData(), 0, in.getLength());
            System.out.println("Đã nhận từ server: " + receivedMessage);

            // Tách thông tin từ phản hồi server
            String[] responseParts = receivedMessage.split(";");
            String requestId = responseParts[0];  // requestId
            int n = Integer.parseInt(responseParts[1].split(",")[0].trim());  // Lấy giá trị n từ server

            // Tính các số nguyên tố đầu tiên
            List<Integer> primeNumbers = getFirstNPrimes(n);

            // Xây dựng chuỗi kết quả
            StringBuilder resultMessage = new StringBuilder(requestId + ";");
            for (int prime : primeNumbers) {
                resultMessage.append(prime).append(",");
            }
            // Loại bỏ dấu phẩy thừa cuối cùng
            resultMessage.setLength(resultMessage.length() - 1);

            // Gửi kết quả lại cho server
            DatagramPacket res = new DatagramPacket(resultMessage.toString().getBytes(), resultMessage.length(), InetAddress.getByName(serverAddress), port);
            socket.send(res);
            System.out.println("Đã gửi kết quả: " + resultMessage.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hàm kiểm tra số nguyên tố
    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Hàm tính n số nguyên tố đầu tiên
    public static List<Integer> getFirstNPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        int number = 2;  // Bắt đầu kiểm tra từ 2

        while (primes.size() < n) {
            if (isPrime(number)) {
                primes.add(number);
            }
            number++;
        }

        return primes;
    }
}

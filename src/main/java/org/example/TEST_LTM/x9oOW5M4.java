package org.example.TEST_LTM;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class x9oOW5M4 {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109"; // Địa chỉ server
        int port = 2206; // Cổng kết nối
        String studentCode = "B21DCCN249"; // Mã sinh viên
        String qCode = "x9oOW5M4"; // Mã câu hỏi
        String message = studentCode + ";" + qCode; // Chuỗi gửi lên server

        try (Socket socket = new Socket(serverAddress, port)) {
            socket.setSoTimeout(5000); // Thời gian chờ tối đa 5 giây

            // Gửi dữ liệu đến server
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes());
            outputStream.write('\n'); // Thêm dấu kết thúc dòng
            outputStream.flush();
            System.out.println("Đã gửi message: " + message);

            // Nhận dữ liệu từ server
            InputStream inputStream = socket.getInputStream();
            StringBuilder responseBuilder = new StringBuilder();
            int ch;
            while ((ch = inputStream.read()) != -1) {
                responseBuilder.append((char) ch);
            }
            String response = responseBuilder.toString().trim(); // Chuỗi nhận được
            System.out.println("Dữ liệu nhận được: " + response);

            // Tính tổng các số nguyên
            String[] numbers = response.split("\\|"); // Phân tách theo dấu "|"
            int sum = 0;
            for (String number : numbers) {
                sum += Integer.parseInt(number); // Tính tổng
            }
            System.out.println("Tổng các số nguyên: " + sum);

            // Gửi tổng lên server
            outputStream.write(String.valueOf(sum).getBytes());
            outputStream.write('\n'); // Thêm dấu kết thúc dòng
            outputStream.flush();
            System.out.println("Đã gửi tổng: " + sum);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

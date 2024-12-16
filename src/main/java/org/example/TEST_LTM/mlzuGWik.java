package org.example.TEST_LTM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class mlzuGWik {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109"; // Địa chỉ server
        int port = 2208; // Cổng kết nối
        String studentCode = "B21DCCN249"; // Mã sinh viên
        String qCode = "mlzuGWik"; // Mã câu hỏi
        String message = studentCode + ";" + qCode; // Chuỗi gửi lên server

        try (Socket socket = new Socket(serverAddress, port)) {
            socket.setSoTimeout(5000); // Thời gian chờ tối đa 5 giây

            // Tạo BufferedWriter để gửi dữ liệu
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(message);
            writer.newLine(); // Thêm dòng mới để gửi
            writer.flush();
            System.out.println("Đã gửi message: " + message);

            // Tạo BufferedReader để nhận dữ liệu
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine(); // Đọc phản hồi từ server

            if (response != null) {
                System.out.println("Dữ liệu nhận được: " + response);

                // Tìm kiếm các tên miền .edu
                String[] domains = response.split(", ");
                List<String> eduDomains = new ArrayList<>();

                for (String domain : domains) {
                    if (domain.endsWith(".edu")) {
                        eduDomains.add(domain);
                    }
                }

                // Gửi các tên miền .edu lên server
                if (!eduDomains.isEmpty()) {
                    String eduMessage = String.join(", ", eduDomains);
                    writer.write(eduMessage);
                    writer.newLine(); // Thêm dòng mới để gửi
                    writer.flush();
                    System.out.println("Đã gửi các tên miền .edu: " + eduMessage);
                } else {
                    System.out.println("Không tìm thấy tên miền .edu nào.");
                }
            } else {
                System.out.println("Không có dữ liệu nhận được từ server.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

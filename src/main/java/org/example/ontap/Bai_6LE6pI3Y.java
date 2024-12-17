package org.example.ontap;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Bai_6LE6pI3Y {
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2208;
        String studentCode = "B21DCCN249";
        String qCode = "6LE6pI3Y";
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
            String serverData = responseParts[1];  // Data từ server

            // Đếm tần suất ký tự và lưu các vị trí của ký tự xuất hiện nhiều nhất
            Map<Character, Integer> charCount = new HashMap<>();
            Map<Character, ArrayList<Integer>> charPositions = new HashMap<>();
            int maxCount = 0;

            // Đếm số lần xuất hiện của từng ký tự và lưu các vị trí
            for (int i = 0; i < serverData.length(); i++) {
                char currentChar = serverData.charAt(i);
                charCount.put(currentChar, charCount.getOrDefault(currentChar, 0) + 1);

                // Lưu vị trí xuất hiện của ký tự
                charPositions.computeIfAbsent(currentChar, k -> new ArrayList<>()).add(i + 1);

                // Cập nhật maxCount và các ký tự có tần suất xuất hiện lớn nhất
                if (charCount.get(currentChar) > maxCount) {
                    maxCount = charCount.get(currentChar);
                }
            }

            // Tìm các ký tự có tần suất xuất hiện lớn nhất
            ArrayList<String> result = new ArrayList<>();
            for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
                if (entry.getValue() == maxCount) {
                    // Lấy các vị trí của ký tự có tần suất lớn nhất
                    StringBuilder positions = new StringBuilder();
                    for (int pos : charPositions.get(entry.getKey())) {
                        positions.append(pos).append(",");
                    }
                    result.add(entry.getKey() + ":" + positions.toString());
                }
            }

            // Tạo thông điệp gửi lại server
            String resultMessage = requestId + ";" + String.join(",", result);
            DatagramPacket res = new DatagramPacket(resultMessage.getBytes(), resultMessage.length(), InetAddress.getByName(serverAddress), port);
            socket.send(res);
            System.out.println("Đã gửi kết quả: " + resultMessage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

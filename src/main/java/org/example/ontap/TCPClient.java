package org.example.ontap;

import java.io.*;
import java.net.*;

public class TCPClient {

    public static final String serverAddress = "203.162.10.109";
    public static final int port = 2206;
    public static final String studentCode = "B21DCCN249";
    public static final String qCode = "1zP78Kmv";
    public static final String message = studentCode + ";" + qCode;

    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;

        try {
            // Tạo kết nối tới server
            socket = new Socket(serverAddress, port);

            // Lấy InputStream và OutputStream của socket
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            // Gửi thông tin sinh viên và câu hỏi tới server
            outputStream.writeUTF(message);
            outputStream.flush();
            System.out.println("Đã gửi: " + message);

            // Kiểm tra nếu server phản hồi trước khi đọc dữ liệu
            if (inputStream.available() > 0) {
                // Đọc dữ liệu nhận từ server (chuỗi số nguyên)
                String serverResponse = inputStream.readUTF();
                System.out.println("Dữ liệu từ server: " + serverResponse);

                // Tách chuỗi nhận được thành mảng số nguyên
                String[] stringNumbers = serverResponse.split(",");
                int[] numbers = new int[stringNumbers.length];
                for (int i = 0; i < stringNumbers.length; i++) {
                    numbers[i] = Integer.parseInt(stringNumbers[i]);
                }

                // Tìm giá trị khoảng cách nhỏ nhất và hai giá trị tạo thành nó
                int[] result = findMinDistance(numbers);

                // Gửi kết quả lại cho server (khoảng cách nhỏ nhất và hai giá trị tạo thành nó)
                String resultMessage = result[0] + "," + result[1] + "," + result[2];
                outputStream.writeUTF(resultMessage);
                outputStream.flush();

                // Đóng kết nối sau khi hoàn thành
                System.out.println("Kết quả đã gửi: " + resultMessage);
            } else {
                System.out.println("Không nhận được phản hồi từ server.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (outputStream != null) outputStream.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm tìm khoảng cách nhỏ nhất và hai giá trị tạo thành nó
    public static int[] findMinDistance(int[] arr) {
        int minDistance = Integer.MAX_VALUE;
        int val1 = 0, val2 = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int distance = Math.abs(arr[i] - arr[j]);
                if (distance < minDistance) {
                    minDistance = distance;
                    val1 = arr[i];
                    val2 = arr[j];
                }
            }
        }

        return new int[] {minDistance, val1, val2};
    }
}

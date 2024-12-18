package B21DCCN326;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ChuoiConDaiNhat {
    public static void main(String[] args) {
        String studentCode = "B21DCCN326";
        String qCode = "e0krvMW0";
        String serverAddress = "203.162.10.109";  // Địa chỉ IP của server
        int port = 2206  ;  // Cổng kết nối
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(serverAddress, port)) {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            // Bước 1: Gửi mã sinh viên và mã câu hỏi
            out.write(message.getBytes());
            out.flush();
            System.out.println("Gửi đến server: " + message);

            // Bước 2: Nhận chuỗi từ server
            byte[] buffer = new byte[1023];
            int data = in.read(buffer);
            String response = new String(buffer, 0, data);
            System.out.println("Nhận từ server: " + response);

            // Bước 3: Tìm chuỗi con dài nhất không có ký tự lặp lại
            String res = findLongestSubstring(response);

            // Bước 4: Gửi chuỗi con dài nhất lên server
            out.write(res.getBytes());
            out.flush();
            System.out.println("Gửi đến server: " + res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm tìm chuỗi con dài nhất không có ký tự lặp lại
        public static String findLongestSubstring(String s) {
        int maxLength = 0;
        String longestSubstring = "";
        StringBuilder currentSubstring = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            String currentChar = String.valueOf(s.charAt(i));
            if (currentSubstring.toString().contains(currentChar)) {
                int index = currentSubstring.indexOf(currentChar);
                currentSubstring.delete(0, index + 1);
            }
            currentSubstring.append(currentChar);
            if (currentSubstring.length() > maxLength) {
                maxLength = currentSubstring.length();
                longestSubstring = currentSubstring.toString();
            }
        }
        return longestSubstring + ";" + maxLength;
    }
}

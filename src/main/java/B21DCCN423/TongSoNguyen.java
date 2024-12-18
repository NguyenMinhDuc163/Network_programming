package B21DCCN423;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TongSoNguyen {
    public static void main(String[] args) {
        String studentCode = "B21DCCN504";
        String qCode = "yc9wvr9T";
        String serverAddress = "203.162.10.109";  // Địa chỉ IP của server
        int port = 2206 ;  // Cổng kết nối
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(serverAddress, port)){
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            out.write(message.getBytes());
            out.flush();
            System.out.println(" da gui " + message);

            byte[] buffer = new byte[1024];
            int data = in.read(buffer);
            String response = new String(buffer, 0, data);
            System.out.println(response);

            String ans = String.valueOf(Arrays.stream(response.split("\\|")).mapToInt(Integer::parseInt).sum());

            out.write(ans.getBytes());
            out.flush();
            System.out.println(ans);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

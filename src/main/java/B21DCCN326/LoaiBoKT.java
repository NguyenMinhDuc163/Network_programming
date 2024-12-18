package B21DCCN326;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class LoaiBoKT {
    public static void main(String[] args) {
        String studentCode = "B21DCCN326";
        String qCode = "rUfu9SKZ";
        String serverAddress = "203.162.10.109";  // Địa chỉ IP của server
        int port = 2208  ;  // Cổng kết nối
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(serverAddress, port)){
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.write(message);
            writer.newLine();
            writer.flush();
            System.out.println(message);

            String s1 = reader.readLine();
            String s2 = reader.readLine();
            System.out.println(s1 + " " + s2);

            String ans = "";
            for(char x: s1.toCharArray()){
                boolean isCheck = true;
                for(char y : s2.toCharArray()){
                    if (x == y) {
                        isCheck = false;
                        break;
                    }
                }
                if(isCheck) ans += x;
            }
            writer.write(ans);
            writer.flush();
            System.out.println(ans);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

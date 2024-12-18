package B21DCCN423;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedHashMap;

public class KiTuXuatHien {
    public static void main(String[] args) {
        String studentCode = "B21DCCN423";
        String qCode = "SFn3Jatf";
        String serverAddress = "203.162.10.109";  // Địa chỉ IP của server
        int port = 2208  ;  // Cổng kết nối
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(serverAddress, port)){
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.write(message);
            writer.newLine();
            writer.flush();
            System.out.println("da gui " + message);

            String response = reader.readLine();
            System.out.println(response);

            LinkedHashMap<Character, Integer> mp = new LinkedHashMap<>();
            for(char x : response.toCharArray()){
                if(Character.isAlphabetic(x) || Character.isDigit(x)){
                    mp.put(x, mp.getOrDefault(x, 0) + 1);
                }
            }

            String res = "";
            for(var x: mp.entrySet()){
                if(x.getValue() > 1){
                    res += x.getKey() + ":" + x.getValue() + ",";
                }
            }

            writer.write(res);
            writer.flush();
            System.out.println(res);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package B21DCCN326;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class PhuongSai {
    public static void main(String[] args) {
        String studentCode = "B21DCCN326";
        String qCode = "JNaLW4JZ";
        String serverAddress = "203.162.10.109";  // Địa chỉ IP của server
        int port = 2207  ;  // Cổng kết nối
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(serverAddress, port)){
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(message);
            out.flush();
            System.out.println(message);

            int n = in.readInt();
            int tong = 0 ;
            float trungBinh = 0, phuongSai = 0;
            ArrayList<Integer> lst = new ArrayList<>();
            for(int i = 0; i < n; i++){
                int tmp = in.readInt();
                tong += tmp;
                lst.add(tmp);
            }
            trungBinh = tong / (float) n;
            for(int x : lst){
                phuongSai += (float) Math.pow((x - trungBinh), 2);
            }
            phuongSai /= n;

            out.writeInt(tong);
            out.flush();
            out.writeFloat(trungBinh);
            out.flush();
            out.writeFloat(phuongSai);
            out.flush();

            System.out.println(tong + " " + trungBinh + " " + phuongSai);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

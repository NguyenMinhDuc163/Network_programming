package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ProductTCP {
    public static void main(String[] args) {
        String studentCode = "B21DCCN326";
        String qCode = "zTjweU46";
        String serverAddress = "203.162.10.109";  // Địa chỉ IP của server
        int port = 2209  ;  // Cổng kết nối
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(serverAddress, port)){
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(message);
            out.flush();

            System.out.println(message);

            Product product = (Product) in.readObject();
            System.out.println(product);

            String s = String.valueOf((int)product.getPrice());
            int sum = 0 ;
            for(char x : s.toCharArray()){
                sum += Integer.parseInt(String.valueOf(x));
            }
            product.setDiscount(sum);
            out.writeObject(product);
            System.out.println(product);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

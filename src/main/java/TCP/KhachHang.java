package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class KhackHang{
    public static void main(String[] args) {
        String studentCode = "B21DCCN423";
        String qCode = "sQ4HJaA7";
        String serverAddress = "203.162.10.109";  // Địa chỉ IP của server
        int port = 2209 ;  // Cổng kết nối
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(serverAddress, port)){
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeObject(message);
            outputStream.flush();
            System.out.println("da gui " + message);

            Customer customer = (Customer) inputStream.readObject();
            System.out.println(customer);
            // ten
            String[] data = customer.getName().split("\\s+");
            String name = data[data.length - 1].toUpperCase() + ", ";
            for(int i = 0; i < data.length - 1; i++){
                data[i] = data[i].substring(0, 1).toUpperCase() + data[i].substring(1).toLowerCase() + " ";
                name += data[i];
            }

            // useName
            String userName = "";
            for(int i = 0 ; i < data.length - 1; i++){
                userName += data[i].substring(0, 1).toLowerCase() ;
            }
            userName += data[data.length - 1].toLowerCase();
            // ns
            data = customer.getDayOfBirth().split("-");
            String birh = data[1] + "/" + data[0] + "/" + data[2];

            customer.setName(name);
            customer.setDayOfBirth(birh);
            customer.setUserName(userName);
            System.out.println(customer);

            outputStream.writeObject(customer);
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
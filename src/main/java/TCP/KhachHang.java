package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Locale;

public class KhachHang {

    public static String normalizeBirth(String s){
        String []birth = s.split("-");
        return birth[1] + "/" + birth[0] + "/" + birth[2];
    }

    public static String normalizeName(String name){
        String[] words = name.trim().split("\\s+");
        String res = "";
        for (String word : words) {
            res += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
        }
        return  words[words.length - 1].toUpperCase() + ", " +  res.substring(0, res.length() - 1).trim();
    }

    public static String createUserName(String name){
        String []s = name.toLowerCase().split("\\s+");
        String userName = "";
        for(int i = 0;i<s.length - 1;i++) userName+=Character.toLowerCase(s[i].charAt(0));
        userName +=  s[s.length - 1].toLowerCase();
        return userName.trim();
    }
    public static void main(String[] args) {
        String serverAddress = "203.162.10.109";
        int port = 2209;
        String message = "B21DCCN249;gQtDFKZE";
        try (Socket socket = new Socket(serverAddress, port)) {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(message);
            output.flush();
            System.out.println("da gui" + message);
            Customer customer = (Customer) input.readObject();
            System.out.println("da nhan " + customer);
            customer.setUserName(createUserName(customer.getName()));
            customer.setName(normalizeName(customer.getName()));
            customer.setDayOfBirth(normalizeBirth(customer.getDayOfBirth()));

            output.writeObject(customer);
            System.out.println("da gui " + customer);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

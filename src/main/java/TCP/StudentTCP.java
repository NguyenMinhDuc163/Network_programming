package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class StudentTCP {
    public static void main(String[] args) {
        String studentCode = "B21DCCN248";
        String qCode = "Ni8e2Ji4";
        String serverAddress = "203.162.10.109";  // Địa chỉ IP của server
        int port = 2209 ;  // Cổng kết nối
        String message = studentCode + ";" + qCode;

        try (Socket socket = new Socket(serverAddress, port)){
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            out.writeObject(message);
            out.flush();
            System.out.println(message);

            Student student = (Student) in.readObject();
            System.out.println(student);

            float gpa = student.getGpa();
            String gpaLetter = "F";
            if(gpa >= 3.7 && gpa <= 4) gpaLetter = "A";
            else if(gpa >= 3) gpaLetter = "B";
            else if (gpa >= 2) gpaLetter = "C";
            else if(gpa >= 1) gpaLetter = "D";

            student.setGpaLetter(gpaLetter);

            out.writeObject(student);
            out.flush();
            System.out.println(student);
        }catch (Exception e){
           e.printStackTrace();
        }
    }
}

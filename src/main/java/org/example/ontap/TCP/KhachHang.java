package org.example.ontap.TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
 class Customer implements Serializable {
    private static final long serialVersionUID = 20170711; // Theo yêu cầu của đề bài
    private int id;
    private String code, name, dayOfBirth, userName;
    public Customer(int id, String code, String name, String dayOfBirth, String userName) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.dayOfBirth = dayOfBirth;
        this.userName = userName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDayOfBirth() {
        return dayOfBirth;
    }
    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
public class KhachHang {
    public static void main(String[] args) throws Exception{
        // Kết nối tới server
        Socket socket = new Socket("203.162.10.109", 2209);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        // a. Gửi mã sinh viên và mã câu hỏi
        String code = "B21DCCN249;gQtDFKZE";
        out.writeObject(code);
        out.flush();
        // b. Nhận đối tượng Customer từ server
        Customer customer = (Customer) in.readObject();
        System.out.println(customer);
        // c. Chuẩn hóa thông tin khách hàng
        //Chuẩn hoá tên
        String []tenTmp = customer.getName().split("\\s+");
        String tenMoi = "";
        tenMoi+=tenTmp[tenTmp.length - 1].toUpperCase()+", ";
        for(int i = 0;i<tenTmp.length - 1;i++) tenMoi+=Character.toUpperCase(tenTmp[i].charAt(0)) + tenTmp[i].substring(1).toLowerCase() + " ";
        customer.setName(tenMoi.trim());
        //Chuẩn hoá ngày sinh
        String []nsTmp = customer.getDayOfBirth().split("-");
        String nsMoi = nsTmp[1] + "/" + nsTmp[0] + "/" + nsTmp[2];
        customer.setDayOfBirth(nsMoi);
        //Tạo username
        String userMoi = "";
        for(int i = 0;i<tenTmp.length - 1;i++) userMoi+=Character.toLowerCase(tenTmp[i].charAt(0));
        userMoi+=tenTmp[tenTmp.length - 1].toLowerCase();
        customer.setUserName(userMoi);
        // d. Gửi lại đối tượng đã được sửa đổi
        System.out.println(customer);
        out.writeObject(customer);
        out.flush();
    }


}
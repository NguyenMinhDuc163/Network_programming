package RMI;

import java.rmi.*;
import java.rmi.registry.*;

public class lphIx89r {
    public static void main(String[] args) throws Exception {
        // Kết nối với RMI Registry Server tại IP và cổng 1099
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099); // Địa chỉ và cổng của RMI Registry
        ObjectService sv = (ObjectService) rg.lookup("RMIObjectService");

        // a. Triệu gọi phương thức requestObject để nhận đối tượng Order từ server
        Order order = (Order) sv.requestObject("B21DCCN249", "lphIx89r");

        System.out.println("Đơn hàng nhận được: " + order);

        // b. Tạo mã orderCode cho đơn hàng
        order.generateOrderCode();
        System.out.println("Mã orderCode đã được tạo: " + order.getOrderCode());

        // c. Cập nhật giá trị orderCode trong đối tượng Order
        // (Đã thực hiện trong phương thức generateOrderCode())

        // d. Triệu gọi phương thức submitObject để gửi đối tượng Order trở lại server
        sv.submitObject("B21DCCN249", "lphIx89r", order);

        // e. Kết thúc chương trình client
        System.out.println("Kết thúc chương trình client.");
    }
}

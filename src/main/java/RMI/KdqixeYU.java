package RMI;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.List;

public class KdqixeYU {

    // Phương thức kiểm tra số đối xứng
    public static boolean isPalindrome(int num) {
        String str = Integer.toString(num);
        int len = str.length();
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        // a. Kết nối tới RMI Server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService dataService = (DataService) rg.lookup("RMIDataService");

        // a. Triệu gọi phương thức requestData để nhận N và K
        int[] range = (int[]) dataService.requestData("B21DCCN249", "KdqixeYU");
        int N = range[0];
        int K = range[1];
        System.out.println("Khoảng kiểm tra: " + N + " đến " + K);

        // b. Xác định tất cả các số nguyên đối xứng trong khoảng từ N đến K
        List<Integer> palindromeNumbers = new ArrayList<>();
        for (int i = N; i < K; i++) {
            if (isPalindrome(i)) {
                palindromeNumbers.add(i);
            }
        }
        System.out.println("Các số đối xứng trong khoảng: " + palindromeNumbers);

        // c. Triệu gọi phương thức submitData để gửi danh sách các số đối xứng trở lại server
        dataService.submitData("B21DCCN249", "KdqixeYU", palindromeNumbers);

        // d. Kết thúc chương trình client
        System.out.println("Kết thúc chương trình client.");
    }
}

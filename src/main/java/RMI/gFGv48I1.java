package RMI;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

public class gFGv48I1 {
    public static void main(String[] args) throws Exception {
        // a. Nhận dữ liệu từ server
        Registry rg = LocateRegistry.getRegistry("203.162.10.109", 1099);  // Kết nối với RMI Registry Server tại địa chỉ 203.162.10.109
        CharacterService sv = (CharacterService) rg.lookup("RMICharacterService");

        // Triệu gọi phương thức requestCharacter để nhận chuỗi từ server
        String inputString = sv.requestCharacter("B21DCCN249", "gFGv48I1");
        System.out.println("Chuỗi nhận từ server: " + inputString);

        // b. Đếm tần số xuất hiện của từng ký tự trong chuỗi
        Map<Character, Integer> frequencyMap = new LinkedHashMap<>();
        for (char c : inputString.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Cấu trúc kết quả tần số ký tự dưới dạng chuỗi
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            result.append("\"").append(entry.getKey()).append("\": ").append(entry.getValue()).append(", ");
        }

        // Loại bỏ dấu ", " ở cuối chuỗi
        if (result.length() > 0) {
            result.setLength(result.length() - 2);
        }

        String formattedResult = "{" + result.toString() + "}";

        System.out.println("Kết quả đếm tần số ký tự: " + formattedResult);

        // c. Triệu gọi phương thức submitCharacter để gửi kết quả trở lại server
        sv.submitCharacter("B21DCCN249", "gFGv48I1", formattedResult);

        // Kết thúc chương trình
        System.out.println("Kết thúc chương trình client.");
    }
}

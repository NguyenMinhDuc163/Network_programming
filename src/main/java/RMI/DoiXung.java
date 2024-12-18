package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class DoiXung {
    public static boolean isPalindrome (int n){
        String s = String.valueOf(n);
        return new StringBuilder(s).reverse().toString().equals(s);
    }


    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService dataService = (DataService) registry.lookup("RMIDataService");

        String response = (String) dataService.requestData("B21DCCN249", "KdqixeYU");
        System.out.println(response);
        String[] s = response.split("; ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        List<Integer> list = new ArrayList<>();
        for(int i = n ; i < k; i++){
            if(isPalindrome(i)){
                list.add(i);
            }
        }

        dataService.submitData("B21DCCN249", "KdqixeYU", list);
        list.forEach(System.out::print);
    }
}

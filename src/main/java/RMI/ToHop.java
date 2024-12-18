package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ToHop {

    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        DataService dataService = (DataService) registry.lookup("RMIDataService");
        String response = (String) dataService.requestData("B21DCCN248", "gYnHwbBX ");
        System.out.println(response);

        String []s = response.split(";");
        List<Integer> list = Arrays.stream(s[0].split(", ")).map(Integer::parseInt).sorted().toList();
        System.out.println(list);
        int []a = new int[1000];
        int n = Integer.parseInt(s[1]);

    }
}

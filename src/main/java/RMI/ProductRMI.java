package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProductRMI {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService");

        Product product = (Product) objectService.requestObject("B21DCCN326", "NqYff9Hm");
        System.out.println(product);

        String code = product.getCode().toUpperCase();
        product.setCode(code);
        double exportPrice = product.getImportPrice() + 0.2 * product.getImportPrice();
        product.setExportPrice(exportPrice);

        objectService.submitObject("B21DCCN326", "NqYff9Hm", product);
        System.out.println(product);
    }
}

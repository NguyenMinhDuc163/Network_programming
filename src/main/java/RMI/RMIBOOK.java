package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIBOOK {
    public static void main(String[] args) throws  Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService");

        BookX book = (BookX) objectService.requestObject("B21DCCN260", "BVyrb7jx");
        System.out.println(book);

        String res = "";
        String author[] = book.getAuthor().split("\\s+");
        res += "" + author[0].charAt(0) + author[author.length - 1].charAt(author[author.length - 1].length() - 1);
       String published =  String.valueOf(book.getYearPublished());
       res += "" +  published.substring(published.length() - 2);
       res += "" + book.getGenre().length();
       res += "" + book.getTitle().length() % 10;
       book.setCode(res);
       objectService.submitObject("B21DCCN260", "BVyrb7jx", book);
        System.out.println(book);

    }
}

package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MaHoa {
    public static void main(String[] args) throws  Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        CharacterService characterService = (CharacterService) registry.lookup("RMICharacterService");

        String response = characterService.requestCharacter("B21DCCN326", "lpUIEu4i");
        System.out.println(response);
    }
}

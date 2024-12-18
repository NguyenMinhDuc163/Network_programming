package RMI;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedHashMap;

public class MangByte {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("203.162.10.109", 1099);
        ByteService byteService = (ByteService) registry.lookup("RMIByteService");

        byte []data = byteService.requestData("B21DCCN260", "NYkdLszt");
        LinkedHashMap<Byte, Integer> mp = new LinkedHashMap<>();
        for(byte x: data){
            mp.put(x, mp.getOrDefault(x, 0) + 1);
            System.out.print(x + " ");
        }
        byte value = 0;
        int max = Integer.MIN_VALUE;
        for(var x: mp.entrySet()){
            if(x.getValue() > max){
                max = x.getValue();
            }
        }

        for(var x: data){
            if(mp.get(x) == max){
                value = x;
                break;
            }
        }
        byte []res = new byte[2];
        res[0] = value;
        res[1] = (byte)max;
        byteService.submitData("B21DCCN260", "NYkdLszt", res);
    }
}

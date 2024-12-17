package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DataService extends Remote {
    public Object requestData(String studentCode, String qCode) throws RemoteException;
    public void submitData(String studentCode, String qCode, Object data) throws RemoteException;
}

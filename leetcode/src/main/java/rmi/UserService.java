package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {

   public User getUserByid(long id) throws RemoteException;
}

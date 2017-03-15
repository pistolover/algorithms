package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserServiceImpl extends UnicastRemoteObject implements UserService{

    protected UserServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public User getUserByid(long id) {
        User user = new User();
        user.setAge(10);
        user.setName("jack");
        user.setDesc("good man");
        user.setId(id);
        return user;
    }
}

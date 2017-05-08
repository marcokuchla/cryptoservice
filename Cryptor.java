import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cryptor extends Remote {
    public MyCipher encrypt(String plainText) throws RemoteException;
    public String decrypt(MyCipher cipher) throws RemoteException;
}

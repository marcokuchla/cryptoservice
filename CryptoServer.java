import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CryptoServer {

    public CryptoServer() {
        try {
            Cryptor c = new CryptorImpl();
            Naming.rebind("//localhost/CryptoService", c);
        } catch (RemoteException | MalformedURLException ex) {
            Logger.getLogger(CryptoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new CryptoServer();
    }

}

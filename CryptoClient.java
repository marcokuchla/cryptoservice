import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CryptoClient {
    public static void main(String[] args) {
        try {
            Cryptor c = (Cryptor) Naming.lookup( "//localhost/CryptoService");
            MyCipher encrypted = c.encrypt("Teste");
            System.out.println("Encriptado: " + encrypted);
            System.out.println("Decriptado: " + c.decrypt(encrypted));
        } catch (RemoteException | NotBoundException | MalformedURLException ex) {
            Logger.getLogger(CryptoClient.class.getName()).log(Level.SEVERE, null, ex);
        }

   }
}

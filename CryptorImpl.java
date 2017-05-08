import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class CryptorImpl extends UnicastRemoteObject implements Cryptor {

    private final SecretKey key;
    private final String IV;
    private final Cipher encryptor;
    private final Cipher decryptor;

    public CryptorImpl() throws RemoteException {
        super();
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            key = keyGenerator.generateKey();
            IV = "0123456789012345";
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes("UTF-8"));
            encryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
            encryptor.init(Cipher.ENCRYPT_MODE, key, iv);
            decryptor = Cipher.getInstance("AES/CBC/PKCS5Padding");
            decryptor.init(Cipher.DECRYPT_MODE, key, iv);
        } catch (NoSuchAlgorithmException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | UnsupportedEncodingException
                | NoSuchPaddingException ex) {
            throw new RemoteException("CryptorImpl init failed", ex);
        }
    }

    @Override
    public MyCipher encrypt(String plainText) throws RemoteException {
        byte[] encryptedBytes = null;
        try {
            encryptedBytes = encryptor.doFinal(plainText.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException
                | IllegalBlockSizeException
                | BadPaddingException ex) {
            throw new RemoteException("Encryption Failed", ex);
        }
        return new MyCipher(encryptedBytes);
    }

    @Override
    public String decrypt(MyCipher cipher) throws RemoteException {
        byte[] decryptedBytes = null;
        try {
            decryptedBytes = decryptor.doFinal(cipher.cipher);
        } catch (IllegalBlockSizeException
                | BadPaddingException ex) {
            throw new RemoteException("Decryption Failed", ex);
        }
        return new String(decryptedBytes);
    }
}

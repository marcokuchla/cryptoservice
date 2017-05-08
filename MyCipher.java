import java.io.Serializable;
import java.util.Base64;

public class MyCipher implements Serializable {
    public byte[] cipher;

    public MyCipher(byte[] cipher) {
        this.cipher = cipher;
    }

    @Override
    public String toString() {
        return "MyCipher{" + "cipher=" + Base64.getEncoder().encodeToString(cipher) + '}';
    }
}

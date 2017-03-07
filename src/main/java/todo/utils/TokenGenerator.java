package todo.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;

public class TokenGenerator {

    public static String generate(final int keyLen){

        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[keyLen/8];
        random.nextBytes(bytes);
        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }

}

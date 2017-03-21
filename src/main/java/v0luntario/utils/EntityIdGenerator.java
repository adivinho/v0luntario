package v0luntario.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by silvo on 3/14/17.
 */
public class EntityIdGenerator {
    public static Long random(){
        Long l = Math.abs(UUID.randomUUID().getLeastSignificantBits());
        return l;
    }

    public static String makeSHA1Hash(String input)
            throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();
        byte[] buffer = input.getBytes("UTF-8");
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return hexStr;
    }

    public static Long randomLong(){
        return Math.abs((UUID.randomUUID().getLeastSignificantBits()));
    }

    public static Short randomShort(){
        return (short) Math.abs((UUID.randomUUID().getLeastSignificantBits()));
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
}

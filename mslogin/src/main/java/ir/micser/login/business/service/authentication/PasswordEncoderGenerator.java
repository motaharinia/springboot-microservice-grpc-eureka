package ir.micser.login.business.service.authentication;

import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.string.RandomGenerationTypeEnum;
import com.motaharinia.msutility.string.StringTools;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoderGenerator {

    public static void main(String[] args) {
        String password ="123456789";
        System.out.println("MsLogin.Hashed:"+getMd5HashedPassword(password));
        System.out.println("MsLogin.Generated:"+PasswordEncoderGenerator.generate(password));
    }
    private static String getMd5HashedPassword(String password) {
        try {
            //make password md5 before make it bcrypt (because convertaion of prestashop md5 passwords)
            String salt = "1dDb1jYTtTzz3xO0aF5JBEsQzn7Ik7j56LO1RJStiSJm5B7Ggydy1Gkc";
            String passwordWithSalt = salt + password;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(passwordWithSalt.getBytes(), 0, passwordWithSalt.length());
            String md5 = new BigInteger(1, m.digest()).toString(16);
            return md5;
        } catch (NoSuchAlgorithmException ex) {
            return "getMd5HashedPassword GENERATE ERROR";
        }
    }

    public static String generate(String password) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(getMd5HashedPassword(password));
        return hashedPassword;
    }

    public static Boolean check(String rawPassword, String hashedPassword){
        if(ObjectUtils.isEmpty(rawPassword) || ObjectUtils.isEmpty(hashedPassword) ){
            return false;
        }
        hashedPassword = hashedPassword.replace("{bcrypt}", "");
        String saltyPassword = getMd5HashedPassword(rawPassword);
//        System.out.println("CustomPasswordEncoder: rawPassword:" + rawPassword + " hashedPassword:" + hashedPassword);
        Boolean checkPassword = false;
        try {

            checkPassword = BCrypt.checkpw(saltyPassword, hashedPassword);
        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            System.out.println(ex.getStackTrace().toString());
//            System.out.println(ex.getLocalizedMessage());
            return false;
        }
        return checkPassword;
    }

    public static String generateRandomHashedPassword() throws UtilityException {
        return generate(generateRandomRawPassword());
    }

    public static String generateRandomRawPassword() throws UtilityException {

        String randomPassword = StringTools.generateRandomString(RandomGenerationTypeEnum.NUMBER, 8, false);
        return randomPassword;
    }
}

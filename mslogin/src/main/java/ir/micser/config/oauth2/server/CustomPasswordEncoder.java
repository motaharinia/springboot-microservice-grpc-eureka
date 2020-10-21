package ir.micser.config.oauth2.server;


import ir.micser.login.business.service.authentication.PasswordEncoderGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String encodedPassword = PasswordEncoderGenerator.generate(rawPassword.toString());
        return encodedPassword;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return PasswordEncoderGenerator.check(rawPassword.toString(), encodedPassword);
    }

}

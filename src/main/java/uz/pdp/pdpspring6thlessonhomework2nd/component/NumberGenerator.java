package uz.pdp.pdpspring6thlessonhomework2nd.component;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class NumberGenerator {

    public String generateRandomPassword(int len) {
        final String chars = "0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < len; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
}

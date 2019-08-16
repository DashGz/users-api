import java.util.Random;

public class TokenService {

    public static String getToken(String user, String pass) {

       if (!user.isEmpty() && !pass.isEmpty()) {

            String token = getSaltString();

            ValidarToken.tokenPrev = token;

            return token;
        }

        return "error";
    }
    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 15) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

}

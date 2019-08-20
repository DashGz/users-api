import java.util.EnumMap;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class TokenService {

    public static String getToken(String user, String pass) {

        // Create a HashMap object called capitalCities
        /*HashMap<String, String> capitalCities = new HashMap<String, String>();

        // Add keys and values (Country, City)
        capitalCities.put("England", "London");
        capitalCities.put("Germany", "Berlin");
        capitalCities.put("Norway", "Oslo");
        capitalCities.put("USA", "Washington DC");
        System.out.println(capitalCities);*/
        HashMap mapaValidar = TokenService.crearMapa();
        //System.out.println(mapaValidar);
       if (!user.isEmpty() && !pass.isEmpty()) {
           /*System.out.println("ingreso al if sin .empty");
           System.out.println(mapaValidar.get(user));
           System.out.println(mapaValidar.get(pass));
           System.out.println(getKeyFromValue(mapaValidar, pass));*/
           Object keyValidar = getKeyFromValue(mapaValidar, pass);
           String.valueOf(keyValidar);
           //System.out.println(keyValidar);

           if (mapaValidar.get(user).equals(pass) && keyValidar.equals(user)) {
               String token = getSaltString();

               ValidarToken.tokenPrev = token;
               return token;
           }


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

    public static HashMap crearMapa(){
        HashMap<String, String> Usuarios = new HashMap<String, String>();
        Usuarios.put("usuario1", "1234");
        Usuarios.put("usuario2", "1234");
        Usuarios.put("usuario3", "1234");
        Usuarios.put("usuario4", "1234");
        //System.out.println(Usuarios);
        return Usuarios;
    }

    public static HashMap crearMapaTokens() {
        HashMap<String, String> Tokens = new HashMap<String, String>();
        return Tokens;
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

}

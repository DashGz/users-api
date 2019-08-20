import com.google.gson.Gson;
import com.google.gson.JsonObject;
import sun.tools.jstat.Token;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class SparkRestFull {

    public static void main(String[] args) {
        //final ISiteService service = new SiteServiceUrlImpl();
        final IUserService service = new SiteServiceUrlImp();
        port(8082);

        //final IUserService service = new SiteServiceUrlImpl();

        /*get("/users", (req, res) -> {
            String respuesta = req.headers("token");
            res.type("application/json");
            return new Gson().toJsonTree(respuesta);
        });*/
        //HashMap mapaUSers = TokenService.crearMapa();
        //System.out.println(mapaUSers);
        //System.out.println(TokenService.crearMapa());

        HashMap mapaTokens = TokenService.crearMapaTokens();


        post("/users", (req, res) -> {
            User user = new Gson().fromJson(req.body(), User.class);
            String name = user.getUsername();
            res.type("application/json");
            String dataToken = TokenService.getToken(user.getUsername(), user.getPassword());
            mapaTokens.put(name, dataToken);
            //System.out.println(mapaTokens);
            return dataToken;
        });

        get("/users/sites", (req1, res1) -> {
            String token = req1.headers("token");
            User user = new Gson().fromJson(req1.body(), User.class);
            String name = user.getUsername();
            //System.out.println(name);
            String pass = user.getPassword();
            //System.out.println(pass);
            //System.out.println(name);
            res1.type("application/json");
            //String val = ValidarToken.validateToken();

            Object keyValidar = TokenService.getKeyFromValue(mapaTokens, token);
            String.valueOf(keyValidar);
            //System.out.println(keyValidar);

            if (/*!token.isEmpty()*//*token.equals(val)*/ mapaTokens.get(name).equals(token) && keyValidar.equals(name) ) {
                Site[] sites = service.getSites();
                return new Gson().toJson(new Gson().toJsonTree(sites));
            }
            else {
                return "ERROR";
            }

        });

        get("/users/sites/:id/categories", (req, res) -> {
            String token = req.headers("token");
            User user = new Gson().fromJson(req.body(), User.class);
            String name = user.getUsername();
            res.type("application/json");
            Object keyValidar = TokenService.getKeyFromValue(mapaTokens, token);
            String.valueOf(keyValidar);
            //String val = ValidarToken.validateToken();
            if (/*token.equals(val)*/mapaTokens.get(name).equals(token) && keyValidar.equals(name)) {
                Category[] categories = service.getCategories(req.params(":id"));
                return new Gson().toJson(new Gson().toJsonTree(categories));
            }
            else {
                return "ERROR";
            }
        });

    }

}

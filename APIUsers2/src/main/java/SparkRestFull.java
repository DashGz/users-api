import com.google.gson.Gson;
import com.google.gson.JsonObject;
import sun.tools.jstat.Token;

import java.util.Collection;

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

        post("/users", (req, res) -> {
            User user = new Gson().fromJson(req.body(), User.class);
            res.type("application/json");
            String dataToken = TokenService.getToken(user.getUsername(), user.getPassword());
            return dataToken;
        });

        get("/users/sites", (req1, res1) -> {
            String token = req1.headers("token");
            //User user1 = new Gson().fromJson(req1.body(), User.class);
            res1.type("application/json");
            String val = ValidarToken.validateToken();
            if (/*!token.isEmpty()*/token.equals(val)) {
                Site[] sites = service.getSites();
                return new Gson().toJson(new Gson().toJsonTree(sites));
            }
            else {
                return "ERROR";
            }

        });

        get("/users/sites/:id/categories", (req, res) -> {
            String token = req.headers("token");
            res.type("application/json");
            String val = ValidarToken.validateToken();
            if (token.equals(val)) {
                Category[] categories = service.getCategories(req.params(":id"));
                return new Gson().toJson(new Gson().toJsonTree(categories));
            }
            else {
                return "ERROR";
            }
        });

    }


}

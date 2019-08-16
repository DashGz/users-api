public class UserService {

    public static boolean revToken(String token) {
        if (!token.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
}

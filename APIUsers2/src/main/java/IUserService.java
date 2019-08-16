public interface IUserService {

    public User[] getUser();
    public Site[] getSites();
    public Category[] getCategories(String id);

}

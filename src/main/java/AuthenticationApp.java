import repositories.Repository;
import services.UserService;

public class AuthenticationApp {

  public static void main(String[] args) {
    Repository app = new Repository();
    app.generateUser();

    UserService userService = new UserService(app);
    userService.printAllDBUser();


  }



}

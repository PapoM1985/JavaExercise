import entities.IAdmin;
import entities.User;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;
import repositories.Repository;
import services.UserService;
import utils.Role;

public class AuthenticationApp {

  public static void main(String[] args) {
    Repository app = new Repository();
    app.generateUser();
    User userConnected;
    IAdmin adminConnected;

    String newName;
    String newSurname;
    String newBirtdate;
    String newUsername;
    String newPassword;
    String newRole;

    int choice;

    UserService userService = new UserService(app);
    userService.printAllDBUser();

    Scanner sc = new Scanner(System.in);
    String message = "Insert your username";
    System.out.println(message);

    String username = sc.nextLine();
    message = "Insert your password";
    System.out.println(message);
    String password = sc.nextLine();

    boolean logOut = true;

    while (logOut) {

      if (userService.AuthenticationUser(username, password).isPresent()) {
        userConnected = userService.findByUsername(username).get();

        message = "Choose the operation you want to perform and enter the corresponding option\n"
            + "Choose: \n"
            + "\t1 - Print my information\n"
            + "\t2 - Reset my password\n";

        System.out.println(message);
        choice = sc.nextInt();

        switch (choice) {
          case 1:
            message = "Yours informations:\n";
            System.out.println(message);
            userService.printUser(userConnected);
            break;
          case 2:
            message = "Now you can update your password\n"
                + "Please enter your password:\n";
            System.out.println(message);
            newPassword = sc.nextLine();
            userConnected.setPassword(newPassword);
            break;
        }

      } else if (userService.AuthenticationAdmin(username, password).isPresent()) {
        adminConnected = userService.AuthenticationAdmin(username, password).get();
        message = "Choose the operation you want to perform and enter the corresponding option\n"
            + "Choose: \n"
            + "\t1 - Add new user\n"
            + "\t2 - Reset password of existing user\n"
            + "\t3 - Print all db existing user\n";
        System.out.println(message);
        choice = sc.nextInt();
        switch (choice) {
          case 1:
            message = "Please insert the following information:\n"
                + "name\n";
            System.out.println(message);
            newName = sc.nextLine();

            message = "surname\n";
            System.out.println(message);
            newSurname = sc.nextLine();

            message = "birtdate\n";
            System.out.println(message);
            newBirtdate = sc.nextLine();

            message = "username\n";
            System.out.println(message);
            newUsername = sc.nextLine();

            message = "password\n";
            System.out.println(message);
            newPassword = sc.nextLine();

            message = "role\n";
            System.out.println(message);
            newRole = sc.nextLine();

            userService.insertUser(userService.findByUsername(username).get(), newName, newSurname,
                newBirtdate, newUsername, newPassword, Role.valueOf(newRole));
            break;
          case 2:
            message = "Please enter the user's username to reset the password.\n"
                + "Username\n";
            System.out.println(message);
            newUsername = sc.nextLine();

            userService.updatePassword(userService.findByUsername(username).get(),
                userService.findByUsername(newUsername).get(), app.generateRandomId());
            break;
          case 3:
            message = "Please enter the user's username to print information.\n"
                + "Username\n";
            System.out.println(message);
            newUsername = sc.nextLine();

            userService.printUser(userService.findByUsername(newUsername).get());
            break;
        }
      } else {
        System.out.println("Credentials not valid");
      }

      message = "Exit?\n Y or N";
      System.out.println(message);
      Scanner input = new Scanner(System.in);
      String answer = input.nextLine().toUpperCase();

      if (answer.equals("Y")) {
        logOut = false;
      }
    }
  }

}



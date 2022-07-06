package services;

import entities.IAdmin;
import entities.User;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import repositories.Repository;
import utils.Role;

public class UserService implements IAdmin {

  Repository userDB;
  private static final int ID_LENGTH = 9;

  public UserService(Repository repository) {
    userDB = repository;
  }


  @Override
  public Optional<User> findByUsername(String username) {
    if (userDB.findByUsername(username).isPresent()) {
      return  Optional.of(userDB.findByUsername(username).get());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public void insertUser(User userConnected, String name, String surname, String birthDate,
      String username,
      String password, Role role) {

    if (userConnected.getRole().equals("ADMIN")) {
      try {
        userDB.insertUser(name, surname, birthDate, username, password, role);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    } else {
      System.out.println("User not enable");
    }
  }

  @Override
  public void updatePassword(User userConnected, User user, String newPassword) {
    if (userConnected.getRole().equals("ADMIN")) {
      user.setPassword(newPassword);
    } else {
    System.out.println("User not enable");
  }
  }

    public Optional<IAdmin> AuthenticationAdmin (String username, String password){
      User userConnected = userDB.findByUsername(username).get();
      if (userConnected.getPassword().equals(password) && userConnected.getRole() == Role.ADMIN){
        return Optional.of((IAdmin) userConnected);
      } else if (userConnected.getPassword().equals(password) && userConnected.getRole() != Role.STANDARD){
        return Optional.empty();
      } else {System.out.println("Password wrong");
        return Optional.empty();}
    }

  public Optional<User> AuthenticationUser (String username, String password){

    if ( Optional.ofNullable(userDB.findByUsername(username).get()).isPresent()) {
      User userConnected = Optional.ofNullable(userDB.findByUsername(username).get()).get();

      if (userConnected.getPassword().equals(password)
          && userConnected.getRole() == Role.STANDARD) {
        return Optional.of(userConnected);
      } else if (userConnected.getPassword().equals(password)
          && userConnected.getRole() != Role.ADMIN) {
        return Optional.empty();
      } else {
        System.out.println("Password wrong");
        return Optional.empty();
      }
    } else {
        return Optional.empty();
      }
  }

    public void printUser (User user){
      System.out.print(
          String.format(
              "User: %s %s \nusername: %s \nbirthdate: %s \nrole: %s \ndate creation: %s \n\n\n",
              user.getName(), user.getSurname(), user.getUsername(), user.getBirthDate(),
              user.getRole(), user.getCreationDate())
      );

    }

    @Override
    public void printAllDBUser () {
      for (User user : userDB.values()) {
        System.out.print(
            String.format(
                "- { <%s> - <%s> } \n", user.getName(), user.getSurname())
        );
      }
    }




  }

package services;

import entities.IAdmin;
import entities.User;
import java.util.Optional;
import repositories.Repository;
import utils.Role;

public class UserService implements IAdmin {

  Repository userDB;

  public UserService(Repository repository) {
    userDB = repository;
  }


  @Override
  public void findByUsername(String username) throws Exception {
    if (userDB.findByUsername(username).isPresent()) {
      printUser(userDB.findByUsername(username).get());
    } else {
      throw new Exception("No user matched");
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
    try {
        User userConnected = userDB.findByUsername(username).get();
     if (userConnected.getPassword().equals(password)){
      if (userConnected.getRole() == Role.ADMIN) {
        return Optional.of( (IAdmin) userConnected); }
      }
    } catch (Exception e){
      e.getMessage();}

      return Optional.empty();
    }

  public Optional<User> AuthenticationUser (String username, String password){
    try {
      User userConnected = userDB.findByUsername(username).get();
      if (userConnected.getPassword().equals(password)){
        if (userConnected.getRole() == Role.STANDARD) {
          return Optional.of( userConnected); }
      }
    } catch (Exception e){
      e.getMessage();}

    return Optional.empty();
  }

    public void printUser (User user){
      System.out.print(
          String.format(
              "User: %s %s \n username: %s \n birthdate: %s \n role: %s \n date creation: %s \n",
              user.getName(), user.getSurname(), user.getUsername(), user.getBirthDate(),
              user.getRole(), user.getCreationDate())
      );

    }

    @Override
    public void printAllDBUser () {
      for (User user : userDB.values()) {
        System.out.print(
            String.format(
                "User: %s %s \n username: %s \n birthdate: %s \n role: %s \n date creation: %s \n",
                user.getName(), user.getSurname(), user.getUsername(), user.getBirthDate(),
                user.getRole(), user.getCreationDate())
        );
      }
    }




  }

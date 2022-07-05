package repositories;

import entities.IAdmin;
import entities.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import utils.Role;

public class Repository implements IAdmin {

  Map<String, User> database;
  private static final int ID_LENGTH = 9;

  public Repository() {
    database = new HashMap<>();
  }

  @Override
  public Optional<User> findByUsername(String username) {
    for (User user : database.values()) {
      if (user.getUsername().equals(username)) {
        return Optional.of(user);
      }
    }
    return Optional.empty();
  }

  @Override
  public void insertUser(String name, String surname, String birthDate, String username,
      String password, Role role) throws Exception {
    if (findByUsername(username).isEmpty()) {
      database.put("id", new User(name, surname, birthDate,
          username.isEmpty() ? generateRandomId() : password, password, role, generateRandomId()));
    } else {
      throw new Exception("User already exists");
    };
  }

  @Override
  public void updatePassword(User user, String newPassword) {
    user.setPassword(newPassword);
  }

  @Override
  public void printUser(User user) {
    System.out.print(
    String.format("User: %s %s \n username: %s \n birthdate: %s \n role: %s \n date creation: %s \n",
        user.getName(),user.getSurname(),user.getUsername(),user.getBirthDate(),user.getRole(),user.getCreationDate())
    );

  }

  @Override
  public void printAllDBUser() {
    for (User user: database.values()){
      System.out.print(
          String.format("User: %s %s \n username: %s \n birthdate: %s \n role: %s \n date creation: %s \n",
              user.getName(),user.getSurname(),user.getUsername(),user.getBirthDate(),user.getRole(),user.getCreationDate())
      );
    }
  }

  public String generateRandomId() {
    String randomString = RandomStringUtils.randomAlphabetic(ID_LENGTH);
    while (database.containsKey(randomString)) {
      randomString = RandomStringUtils.randomAlphabetic(ID_LENGTH);
    }
    return randomString;
  }
}

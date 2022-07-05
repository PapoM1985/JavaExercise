package repositories;

import entities.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import utils.Role;

public class Repository {

  Map<String, User> database;
  private static final int ID_LENGTH = 9;

  public Repository() {
    database = new HashMap<>();
  }

  public Optional<User> findByUsername(String username) {
    return database.values().stream().filter(l -> l.getUsername().equals(username)).findFirst();
    /*    for (User user : database.values()) {
      if (user.getUsername().equals(username)) {
        return Optional.of(user);
      }
    }
    return Optional.empty();*/
  }

  public void insertUser(String name, String surname, String birthDate, String username,
      String password, Role role) throws Exception {

    if (findByUsername(username).isEmpty()) {
      String id = generateRandomId();
      database.put(id,
          new User(name, surname, birthDate,username, password.isEmpty() ? generateRandomId() : password,
              role, id));
    } else {
      throw new Exception("User already exists");
    };
  }

  public String generateRandomId() {
    String randomString = RandomStringUtils.randomAlphabetic(ID_LENGTH);
    while (database.containsKey(randomString)) {
      randomString = RandomStringUtils.randomAlphabetic(ID_LENGTH);
    }

    return randomString;
  }

  public ArrayList<User> values() {
    ArrayList<User> valueOnly = new ArrayList<>();
    for (User user: database.values()){
      valueOnly.add(user);
    }
    return valueOnly;
  }

}

package repositories;

import entities.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.RandomStringUtils;
import services.UserService;
import utils.Role;

public class Repository {

  private final Map<String, User> database;
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

  public void generateUser() {
    User s1 = new User("Franco","Rossi","01012022","frossi","", Role.STANDARD, "");
    User s2 = new User("valerio","Rossi","01012022","frossi","", Role.ADMIN, "");
    User s3 = new User("Giulio","Rossi","01012022","frossi","", Role.STANDARD, "");
    User s4 = new User("Pippo","Rossi","01012022","frossi","", Role.STANDARD, "");
    User s5 = new User("Pluto","Rossi","01012022","frossi","", Role.STANDARD, "");
    User s6 = new User("Paperino","Rossi","01012022","frossi","", Role.STANDARD, "");
    User s7 = new User("Clarabella","Rossi","01012022","frossi","", Role.STANDARD, "");
    User s8 = new User("Roberto","Rossi","01012022","frossi","", Role.STANDARD, "");
    User s9 = new User("John","Rossi","01012022","frossi","", Role.STANDARD, "");
    User s10 = new User("Gigi","Rossi","01012022","frossi","", Role.STANDARD, "");
    database.put(s1.getId(),s1);
    database.put(s2.getId(),s2);
    database.put(s3.getId(),s3);
    database.put(s4.getId(),s4);
    database.put(s5.getId(),s5);
    database.put(s6.getId(),s6);
    database.put(s7.getId(),s7);
    database.put(s8.getId(),s8);
    database.put(s9.getId(),s9);
    database.put(s10.getId(),s10);

  }

}

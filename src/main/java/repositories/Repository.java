package repositories;

import entities.IAdmin;
import entities.User;
import java.util.HashMap;
import java.util.Map;

public class Repository implements IAdmin {

  Map<String,User> database;

  public Repository(){
    database = new HashMap<>();
  }

  @Override
  public User findByUsername(String username) {
    return null;
  }

  @Override
  public void insertUser(String name, String surname, String birthDate, String username,
      String password, String role) {

  }

  @Override
  public void updatePassword(String username) {

  }

  @Override
  public void printUser() {

  }

  @Override
  public void printAllDBUser() {

  }
}

package entities;

import java.util.Optional;

public interface IAdmin {

  Optional<User> findByUsername(String username);
  void insertUser(String name, String surname, String birthDate,String username,String password, String role);
  void updatePassword(String username);
  void printUser();
  void printAllDBUser();

}

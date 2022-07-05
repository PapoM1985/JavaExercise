package entities;

import java.util.Optional;
import utils.Role;

public interface IAdmin {

  Optional<User> findByUsername(String username);
  void insertUser(String name, String surname, String birthDate, String username,
      String password, Role role) throws Exception;

  void updatePassword(User user,String newPassword);

  void printUser(User user) ;
  void printAllDBUser();

}

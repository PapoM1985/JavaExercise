package entities;

import java.util.Optional;
import utils.Role;

public interface IAdmin {

  Optional<User> findByUsername(String username) throws Exception;
  void insertUser(User userConnected, String name, String surname, String birthDate, String username,
      String password, Role role);

  void updatePassword(User userConnected, User user, String newPassword);

  void printUser(User user) ;
  void printAllDBUser();

}

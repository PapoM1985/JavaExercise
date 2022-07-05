package entities;

public interface IAdmin {

  User findByUsername(String username);
  void insertUser(String name, String surname, String birthDate,String username,String password, String role);
  void updatePassword(String username);
  void printUser();
  void printAllDBUser();

}

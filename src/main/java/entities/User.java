package entities;

import java.text.DateFormat;
import java.util.Locale;
import utils.Role;

public class User {

  private String name;
  private String surname;
  private String birthDate;
  private String password;
  private final String username;
  private final String id;
  private final String creationDate;
  private final Role role;

  public User(String name, String surname, String birthDate, String username, String password, Role role, String id) {
    this.name = name;
    this.surname = surname;
    this.birthDate = birthDate;
    this.username = username;
    this.password = password;
    this.id = id;
    this.creationDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ITALY).toString();
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public Role getRole() {
    return role;
  }
}

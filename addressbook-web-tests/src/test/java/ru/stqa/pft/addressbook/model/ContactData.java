package ru.stqa.pft.addressbook.model;
import java.util.Objects;

public class ContactData {

  private int id;
  private final String firstname;
  private final String lastname;
  private final String nickname;
  private final String address;
  private final String numberPhone;
  private final String email;
  private String group;
  public ContactData(int id, String firstname, String lastname, String nickname, String address, String numberPhone, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.numberPhone = numberPhone;
    this.email = email;
    this.group = group;
  }

  public ContactData(String firstname, String lastname, String nickname, String address, String numberPhone, String email, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.numberPhone = numberPhone;
    this.email = email;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getAddress() {
    return address;
  }

  public String getNumberPhone() {
    return numberPhone;
  }

  public String getEmail() {
    return email;
  }
  public int getId() { return id; }

  public void setId(int id) {
    this.id = id;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }
  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", name='" + firstname + '\'' +
            ", last_name='" + lastname + '\'' +
            '}';
  }
}

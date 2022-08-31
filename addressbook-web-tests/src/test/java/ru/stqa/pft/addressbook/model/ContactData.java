package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String nickname;
  private final String address;
  private final String numberPhone;
  private final String email;

  public ContactData(String firstname, String lastname, String nickname, String address, String numberPhone, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.nickname = nickname;
    this.address = address;
    this.numberPhone = numberPhone;
    this.email = email;
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
}

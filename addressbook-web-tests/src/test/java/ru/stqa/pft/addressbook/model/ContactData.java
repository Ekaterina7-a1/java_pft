package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;

import java.util.Objects;
import java.io.File;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.annotations.Type;

import javax.persistence.*;
@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String firstname;
  @Expose
  @Column(name = "lastname")
  private String lastname;
  @Expose
  @Column(name = "nickname")
  private String nickname;
  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;
  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;
  @Expose
  @Column(name = "phone2")
  @Type(type = "text")
  private String phone2;
  @Expose
  @Transient
  private String allPhones;
  @Expose
  @Column(name = "email")
  @Type(type="text")
  private String email;
  @Expose
  @Column(name = "email2")
  @Type(type="text")
  private String email2;
  @Expose
  @Column(name = "email3")
  @Type(type="text")
  private String email3;
  @Expose
  @Transient
  private String group;
  @Expose
  @Transient
  private String allEmails;
  @Expose
  @Column(name="photo")
  @Type(type ="text")
  private String photo;
  public String getAllEmails() {
    return allEmails;
  }


  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
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

  public String getEmail() {
    return email;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getPhone2() {
    return phone2;
  }

  public String getGroup() {
    return group;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public int getId() {
    return id;
  }

  public File getPhoto() { return new File(photo); }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withHomePhone(String home) {
    this.homePhone = home;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobilePhone = mobile;
    return this;
  }

  public ContactData withPhone2(String phone2) {
    this.phone2 = phone2;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) &&  Objects.equals(nickname, that.nickname) && Objects.equals(address, that.address) && Objects.equals(homePhone, that.homePhone) && Objects.equals(mobilePhone, that.mobilePhone) && Objects.equals(workPhone, that.workPhone) && Objects.equals(phone2, that.phone2) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, nickname, address, homePhone, mobilePhone, workPhone, phone2, email, email2, email3);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}

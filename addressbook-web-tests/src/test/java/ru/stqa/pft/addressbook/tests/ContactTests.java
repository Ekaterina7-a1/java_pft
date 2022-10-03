package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    ContactData contact = new ContactData().
            withFirstname("Ekaterina").withLastname("Leonkina").withNickname("leokate")
            .withAddress("city").withHomePhone("123456789").withMobile("+79997777777").withWorkPhone("9998888888")
            .withEmail("test@yandex.ru").withEmail2("ksat@yandex.ru").withEmail3("kaytetest@yandex.ru").withGroup("test1");
    if (app.contact().all().size() == 0) {
      app.contact().createContact(contact);
    }
  }

  @Test
  public void testContactEmails(){
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s)-> !s.equals(""))
            .map(ContactTests::cleanedemail)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedemail (String email) {
    return email.replaceAll("\\s","").replaceAll("[-().]]","");
  }
  @Test
  public void testContactPhones(){
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
            .stream().filter((s)-> !s.equals(""))
            .map(ContactTests::cleanedphone)
            .collect(Collectors.joining("\n"));
  }
  public static String cleanedphone (String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]]","");
  }
  @Test
  public void testContactAddresses() {
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneAdressEmailTests extends TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    ContactData contact = new ContactData().
            withFirstname("Ekaterina").withLastname("Leonkina").withNickname("leokate")
            .withAddress("city").withHomePhone("123456789").withMobile("+79997777777").withWorkPhone("9998888888").withPhone2("+7(555)111-55-25").withEmail("test@yandex.ru").
            withEmail2("ksat@yandex.ru").withEmail3("kaytetest@yandex.ru").withGroup("test1");
    if (app.contact().all().size() == 0) {
      app.contact().createContact(contact);
    }
  }

  @Test
  public void testContactPhonesAddressesEmails(){
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    System.out.println("All phones are: " + contact.getAllPhones());
    System.out.println("Merged phones: " + mergePhones(contactInfoFromEditForm));
    assertThat((contact.getAllPhones()), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone(),contact.getPhone2())
            .stream().filter((s)-> !s.equals(""))
            .map(ContactPhoneAdressEmailTests::cleanedPhones)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
            .stream().filter((s)-> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedPhones (String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }

}
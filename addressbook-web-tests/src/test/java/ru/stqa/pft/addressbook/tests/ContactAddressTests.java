package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    ContactData contactData = new ContactData()
            .withFirstname("name").withLastname("lastname").withNickname("nickname")
            .withAddress("city").withHomePhone("123456789").withMobile("+79997777777").withWorkPhone("9998888888")
            .withEmail("test@yandex.ru").withEmail2("ksat@yandex.ru").withEmail3("kaytetest@yandex.ru").withGroup("test1");
    if (app.contact().all().size() == 0) {
      ContactData contact = new ContactData()
              .withFirstname("Ekaterina").withLastname("Leonkina").withNickname("leokate").withAddress("Moscow city").withMobile("9251536358").withEmail("leonk-ekaterina@yandex.ru").withGroup("test1");
      app.contact().createContact(contact);
    }
  }

  @Test
  public void testContactAddresses() {
    app.goTo().gotoHomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}
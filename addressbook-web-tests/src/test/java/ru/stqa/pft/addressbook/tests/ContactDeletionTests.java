package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    File photo = new File("src/test/resources/bob.jpg");
    if (app.db().contacts().size() == 0) {
      ContactData contact = new ContactData().
              withFirstname("Ekaterina").withLastname("Leonkina").withNickname("leokate")
              .withAddress("city").withHomePhone("123456789").withMobile("+79997777777").withWorkPhone("9998888888").withPhone2("+7(555)111-55-25").withEmail("test@yandex.ru").
              withEmail2("ksat@yandex.ru").withEmail3("kaytetest@yandex.ru").withGroup("test1").withEmail("test@mail.ru").withEmail2("test2@mail.ru").withEmail3("test3@mail.ru").withPhoto(photo);
      app.contact().createContact(contact);
      ;
    }
  }
  @Test
  public void testContactDeletion() throws Exception {
    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
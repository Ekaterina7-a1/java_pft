package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactCreationTests extends TestBase{
  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    File photo = new File ("src/test/resources/bob.jpg");
    ContactData contact = new ContactData().
    withFirstname("Ekaterina").withLastname("Leonkina").withNickname("leokate")
            .withAddress("city").withHomePhone("123456789").withMobile("+79997777777").withWorkPhone("9998888888").withPhone2("+7(555)111-55-25").withEmail("test@yandex.ru").
            withEmail2("ksat@yandex.ru").withEmail3("kaytetest@yandex.ru").withGroup("test1").withPhoto(photo);
    app.contact().createContact(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after,equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}

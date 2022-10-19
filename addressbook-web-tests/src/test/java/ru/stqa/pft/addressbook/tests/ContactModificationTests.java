package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.internal.collections.Pair.create;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
      File photo = new File("src/test/resources/bob.jpg");
      ContactData contactData = new ContactData().
              withFirstname("Ekaterina").withLastname("Leonkina").withNickname("leokate")
              .withAddress("city").withHomePhone("123456789").withMobile("+79997777777").withWorkPhone("9998888888").withPhone2("+7(555)111-55-25").withEmail("test@yandex.ru").
              withEmail2("ksat@yandex.ru").withEmail3("kaytetest@yandex.ru").withGroup("test1").withPhoto(photo);
      GroupData groupData = new GroupData().withName("test1");
    if (app.db().contacts().size() == 0) {
      app.goTo().gotoHomePage();
      create(groupData, contactData);
    }
  }

  @Test
  public void testContactModification() {
    File photo = new File("src/test/resources/bob.jpg");
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contactData2 = new ContactData().withId(modifiedContact.getId()).withFirstname("Katya").withLastname("Leonkina").withNickname("leokate")
            .withAddress("city").withHomePhone("123456789").withMobile("+6868524").withWorkPhone("5555")
            .withEmail("testik@yandex.ru").withEmail2("ksat@yandex.ru").withEmail3("kaytetest@yandex.ru").withGroup("test1").withPhoto(photo);
    app.contact().modify(contactData2);
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(modifiedContact)));
    verifyContactListInUI();
  }
}
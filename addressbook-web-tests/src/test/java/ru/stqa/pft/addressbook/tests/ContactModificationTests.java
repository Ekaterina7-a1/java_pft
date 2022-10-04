package ru.stqa.pft.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      ContactData contact = new ContactData().
              withFirstname("Ekaterina").withLastname("Leonkina").withNickname("leokate")
              .withAddress("city").withHomePhone("123456789").withMobile("+79997777777").withWorkPhone("9998888888").withPhone2("+7(555)111-55-25").withEmail("test@yandex.ru").
              withEmail2("ksat@yandex.ru").withEmail3("kaytetest@yandex.ru").withGroup("test1");
      app.contact().createContact(contact);
      ;
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contactData2 = new ContactData().withId(modifiedContact.getId()).withFirstname("Katya").withLastname("Leonkina").withNickname("leokate")
            .withAddress("city").withHomePhone("123456789").withMobile("+6868524").withWorkPhone("5555")
            .withEmail("testik@yandex.ru").withEmail2("ksat@yandex.ru").withEmail3("kaytetest@yandex.ru").withGroup("test1");
    app.contact().modify(before, contactData2);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());
    before.remove(modifiedContact);
    before.add(contactData2);
    Assert.assertEquals(before, after);
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedContact).withAdded(contactData2)));
  }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() throws Exception {
    app.getContactHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().gotoAddNewPage();
      app.getContactHelper().createContact(new ContactData("Ekaterina", "Leonkina", "leokate", "Moscow city", "9251536358", "leonk-ekaterina@yandex.ru", "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
  }
}
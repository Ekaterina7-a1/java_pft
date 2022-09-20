package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion() throws Exception {
    ContactData contact=new ContactData("Ekaterina", "Leonkina", "leokate", "Moscow city", "9251536358", "leonk-ekaterina@yandex.ru", "test1");
    app.getContactHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().gotoAddNewPage();
      app.getContactHelper().createContact(contact);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().deleteContact();
    app.wd.switchTo().alert().accept();
    app.getContactHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size()-1);
    Assert.assertEquals(before,after);
  }
}
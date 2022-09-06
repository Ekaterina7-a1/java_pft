package ru.stqa.pft.addressbook.tests;

import ru.stqa.pft.addressbook.model.ContactData;
import org.testng.annotations.*;
import org.testng.annotations.Test;
public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoAddNewPage();
    app.getContactHelper().createContact(new ContactData("Ekaterina", "Leonkina", "leokate", "Moscow city", "9251536358", "leonk-ekaterina@yandex.ru", "test1"));
  }
}

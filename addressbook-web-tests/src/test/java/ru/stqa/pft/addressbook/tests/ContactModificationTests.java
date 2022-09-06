package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getContactHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Katya", "Leonkina", "leokate", "Moscow city", "9251536358", "leonk-ekaterina@yandex.ru", null));
    app.getContactHelper().submitContactModification();
  }
}

package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

public class ContactDeleteFromGroupTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
    Groups groups = app.db().groups();
    ContactData contactBeforeAddingToGroup = app.db().contacts().iterator().next();
    Groups beforeGroups = contactBeforeAddingToGroup.getGroups();
    if (groups.size() == 0 || groups.size() == beforeGroups.size()) {
      app.goTo().groupPage();
      app.group().create(group);
      app.goTo().groupPage();
    }
    File photo = new File("src/test/resources/bob.jpg");
    ContactData contactData = new ContactData()
            .withFirstname("Katya").withLastname("Leonkina").withNickname("leokate")
            .withAddress("city").withHomePhone("123456789").withMobile("+6868524").withWorkPhone("5555")
            .withEmail("testik@yandex.ru").withEmail2("ksat@yandex.ru").withEmail3("kaytetest@yandex.ru").withGroup("test1").withPhoto(photo);
    if (app.db().contacts().size() == 0) {
      create(contactData);
    }
  }

  @Test
  public void deleteGroupFromContact() {
    Groups groups = app.db().groups();
    ContactData contactBeforeDeleteToGroup = app.db().contacts().iterator().next();
    Groups beforeGroups = contactBeforeDeleteToGroup.getGroups();
    System.out.println("beforeGroups " + beforeGroups);
    GroupData groupForRemove = beforeGroups.stream().iterator().next();
    app.contact().deleteFromGroup(contactBeforeDeleteToGroup, groupForRemove);
    Groups afterGroups = app.db().contact(contactBeforeDeleteToGroup.getId()).getGroups();
    MatcherAssert.assertThat(afterGroups, CoreMatchers.equalTo(
            beforeGroups.without(groupForRemove)));


  }
}
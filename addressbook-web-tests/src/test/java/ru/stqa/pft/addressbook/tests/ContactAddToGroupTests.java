package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {
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
  public void addContactToGroupTest() {
    Contacts beforeContact = app.db().contacts();
    ContactData contactSelect = beforeContact.iterator().next();
    Groups beforeGroup = app.db().groups();
    GroupData groupSelect = beforeGroup.iterator().next();
    app.goTo().gotoHomePage();
    if (!contactSelect.getGroups().isEmpty() && contactSelect.getGroups().contains(groupSelect)) {
      app.contact().deleteContactFromGroup(contactSelect, groupSelect);
      assertThat(contactSelect.getGroups().without(groupSelect), equalTo(app.db().contacts().stream().
              filter((c) -> c.getId() == contactSelect.getId()).collect(Collectors.toList()).get(0).getGroups()));
      app.goTo().gotoHomePage();
    }
    app.contact().selectAllGroup("[all]");
    app.contact().addToGroup(contactSelect, groupSelect);
    assertThat(contactSelect.getGroups().withAdded(groupSelect), equalTo(app.db().contacts().stream().
            filter((c) -> c.getId() == contactSelect.getId()).collect(Collectors.toList()).get(0).getGroups()));
  }
}
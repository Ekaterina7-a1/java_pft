package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() throws Exception {
    app.stop();
  }

  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream().map((g) -> new GroupData()
              .withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
    }
  }

  public void createGroupIfItNotExists(GroupData groupData) {
    if (!app.contact().isThereAGroupAtContactCreationForm(groupData.getName())) {
      app.goTo().groupPage();
      app.group().create(groupData);
    }
  }
  public void verifyContactListInUI() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();
      assertThat(uiContacts, equalTo(dbContacts.stream().map((g) -> new ContactData()
              .withId(g.getId()).withFirstname(g.getFirstname())).collect(Collectors.toSet())));
    }
  }
  public void create(GroupData groupData, ContactData contactData) {
    app.contact().initContactCreation();
    createGroupIfItNotExists(groupData);
    app.contact().createContact(contactData);
    app.contact().gotoHomePage();
  }
  public void create( ContactData contactData) {
    app.contact().initContactCreation();
    app.contact().createContact(contactData);
    app.contact().gotoHomePage();
  }

}

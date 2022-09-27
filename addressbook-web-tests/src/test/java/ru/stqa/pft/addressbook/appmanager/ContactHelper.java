package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getNumberPhone());
    type(By.name("email"), contactData.getEmail());
  }

  public void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void gotoAddNewPage() {
    click(By.linkText("add new"));
  }

  public void initContactModification(int id) {
    click(By.xpath("//a[@href='edit.php?id=" + id + "']"));
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public void gotoHomePage() {
    click(By.linkText("home"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void createContact(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData, true);
    submitContactCreation();
    gotoHomePage();
  }

  public void submitContactCreation() {
    click(By.name("submit"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void gotoContactPage() {
    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
            && isElementPresent(By.name("submit"))) {
      return;
    }
    click(By.linkText("add new"));
  }

  public void modify(ContactData contactData) {
    selectContactById(contactData.getId());
    initContactModification(contactData.getId());
    fillContactForm(contactData, false);
    submitContactModification();
    gotoHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    wd.switchTo().alert().accept();
    gotoHomePage();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String lastname = element.findElement(By.cssSelector("tr[name='entry'] > td:nth-child(2)")).getText();
      String firstname = element.findElement(By.cssSelector("tr[name='entry'] > td:nth-child(3)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }
}

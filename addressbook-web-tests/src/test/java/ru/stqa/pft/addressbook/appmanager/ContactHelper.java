package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
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
  public void gotoAddNewPage() {
    click(By.linkText("add new"));
  }
  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }
  public void submitContactModification() {
    click(By.name("update"));
  }
  public void gotoHomePage() {
    click(By.linkText("home"));
  }
  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
  public void deleteContact() {click(By.xpath("//input[@value='Delete']")); }

  public void createContact(ContactData contact) {
    gotoAddNewPage();
    fillContactForm(contact, true);
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


  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      String lastname = element.findElement(By.cssSelector("tr[name='entry'] > td:nth-child(2)")).getText();
      String firstname = element.findElement(By.cssSelector("tr[name='entry'] > td:nth-child(3)")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
      ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}

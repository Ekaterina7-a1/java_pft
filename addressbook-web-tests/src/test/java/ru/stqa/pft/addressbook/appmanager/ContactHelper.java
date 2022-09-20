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

  public void fillContactForm(ContactData contactData) {
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

  public void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContact() {
    click(By.name("submit"));
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
    initContactCreation();
    fillContactForm(contact);
    submitContact();
    gotoHomePage();
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

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form/input[22]"));
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//*[@id='maintable']/tbody/tr[@name = 'entry']"));
    for(WebElement element : elements){
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstname = element.findElement(By.xpath("//*[@id='maintable']/tbody/tr[@name = 'entry']/td[3]")).getText();
      String lastname =  element.findElement(By.xpath("//*[@id='maintable']/tbody/tr[@name = 'entry']/td[2]")).getText();
      ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}

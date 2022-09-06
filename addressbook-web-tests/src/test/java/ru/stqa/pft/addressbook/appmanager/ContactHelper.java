package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase{
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
    click(By.xpath("//div[@id='content']/form/input[21]"));
    if (isElementPresent(By.name("new_group"))) {
      new Select((WebElement) By.name("new_group")).selectByVisibleText(contactData.getGroup());
    }
  }

  public void gotoAddNewPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void initContactCreation() {
    click(By.name("firstname"));
  }

  public void initContactModification() { click(By.xpath("//table[@id='maintable']/tbody/tr[3]/td[8]/a/img"));
  }
  public void submitContactModification() {click(By.name("update"));  }

  public void gotoHomePage() { click(By.linkText("home"));
  }

  public void selectContact() { click(By.name("selected[]"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contact) {
    gotoContactPage();
    initContactCreation();
    fillContactForm(contact);
    submitContactModification();
    gotoHomePage();
  }

  public boolean isThereAContact() {
    if (isElementPresent(By.name("entry"))) {
      return isElementPresent(By.name("selected[]"));
    } else {
      return false;
    }
  }
  public void gotoContactPage() {
    if(isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
            && isElementPresent(By.name("submit"))){
      return;
    }
    click(By.linkText("add new"));
  }
}

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

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
}

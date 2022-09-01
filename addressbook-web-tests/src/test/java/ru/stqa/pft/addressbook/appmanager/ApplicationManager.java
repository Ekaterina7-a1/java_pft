package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class ApplicationManager {
  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  public NavigationHelper navigationHelper;
  private GroupHepler groupHepler;
  private JavascriptExecutor js;
  private ChromeDriver wd;

  public ApplicationManager() {
    this.contactHelper = contactHelper;
  }
  public void init() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    js = (JavascriptExecutor) wd;
    wd.get("http://localhost/addressbook/edit.php");
    groupHepler = new GroupHepler(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }
  public GroupHepler getGroupHepler() {
    return groupHepler;
  }
  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
  public void stop() {
    wd.quit();
  }
}

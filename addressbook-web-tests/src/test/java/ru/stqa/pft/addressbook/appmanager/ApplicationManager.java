package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.BrowserType;

import java.time.Duration;


public class ApplicationManager {
  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  public NavigationHelper navigationHelper;
  private GroupHepler groupHepler;
  private JavascriptExecutor js;
  public WebDriver wd;
  private String browser;

  public ApplicationManager() {
    this.contactHelper = contactHelper;
  }

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    }  else if (browser.equals(BrowserType.EDGE)) {
      wd = new EdgeDriver();
    }
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    js = (JavascriptExecutor) wd;
    wd.get("http://localhost/addressbook");
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

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.IOException;
import java.time.Duration;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
public class ApplicationManager {
  private final Properties properties;
  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  public NavigationHelper navigationHelper;
  private GroupHepler groupHepler;
  private JavascriptExecutor js;
  WebDriver wd;
  private String browser;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

    public void init() throws IOException {
      String target = System.getProperty("target", "local");
      properties.load((new FileReader(new File(String.format("src/test/resources/%s.properties", target)))));
    if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.EDGE)) {
      wd = new EdgeDriver();
    }
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
    js = (JavascriptExecutor) wd;
    wd.get(properties.getProperty("web.baseUrl"));
    groupHepler = new GroupHepler(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public GroupHepler group() {
    return groupHepler;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public void stop() {
    wd.quit();
  }
}

package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class ApplicationManager {
  public ChromeDriver wd;
  private SessionHelper sessionHelper;
  public NavigationHelper navigationHelper;
  private GroupHepler groupHepler;
  private JavascriptExecutor js;
  public void init() {
    wd = new ChromeDriver();
    wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    js = (JavascriptExecutor) wd;
    sessionHelper.login("admin", "secret");
    groupHepler = new GroupHepler(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
  }
  public void stop() {
    wd.quit();
  }
  public GroupHepler getGroupHepler() {
    return groupHepler;
  }
  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHepler().initGroupCreation();
    app.getGroupHepler().fillGroupForm(new GroupData("test1", null, null));
    app.getGroupHepler().submitGroupCreation();
    app.getGroupHepler().returnToGroupPage();
  }
}
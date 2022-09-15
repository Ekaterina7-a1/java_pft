package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification () {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHepler().isThereAGroup()){
      app.getGroupHepler().createGroup(new GroupData("test1", "test2", null));
    }
    List<GroupData> before = app.getGroupHepler().getGroupList();
    app.getGroupHepler().selectGroup(before.size() - 1);
    app.getGroupHepler().initGroupModification();
    app.getGroupHepler().fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.getGroupHepler().submitGroupModification();
    app.getGroupHepler().returnToGroupPage();
    List<GroupData> after = app.getGroupHepler().getGroupList();
    Assert.assertEquals(after.size(), before.size());
  }
}

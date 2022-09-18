package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHepler().isThereAGroup()){
      app.getGroupHepler().createGroup(new GroupData("test1", null, null));
    }
    List<GroupData> before = app.getGroupHepler().getGroupList();
    app.getGroupHepler().selectGroup(before.size() - 1);
    app.getGroupHepler().deleteSelectedGroups();
    app.getGroupHepler().returnToGroupPage();
    List<GroupData> after = app.getGroupHepler().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}

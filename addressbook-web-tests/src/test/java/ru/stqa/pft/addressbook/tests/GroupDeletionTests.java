package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHepler().isThereAGroup()){
      app.getGroupHepler().createGroup(new GroupData("test1", null, null));
    }
    app.getGroupHepler().selectGroup();
    app.getGroupHepler().deleteSelectedGroups();
    app.getGroupHepler().returnToGroupPage();
  }

}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletionInGroupTest extends TestBase {
    int selectedGroupId;
    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().contacts().size()==0){
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMobile("89993345223").withEmail("ii@relex.ru"));
        }
        if (app.db().groups().size()==0){
            app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
        }
        Groups groups = app.db().groups();
        GroupData groupForDeletion = groups.iterator().next();
        System.out.println(groupForDeletion);
        selectedGroupId = groupForDeletion.getId();

        app.contact().selectGroup(groupForDeletion.getName());
        if (app.db().contactsInGroup(selectedGroupId).size()==0){
            ContactData contactForAdded = app.contact().all().iterator().next();
            app.contact().addInGroup(contactForAdded, groupForDeletion);
        }
    }

    @Test
    public void testDeletionInGroup(){
        Contacts before = app.db().contactsInGroup(selectedGroupId);
        ContactData contactForDeletion = before.iterator().next();
        System.out.println(contactForDeletion);
        app.contact().deleteFromGroup(contactForDeletion);
        Contacts after = app.db().contactsInGroup(selectedGroupId);
        assertThat(after, equalTo(before.withOut(contactForDeletion)));
    }
}

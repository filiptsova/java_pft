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
    String selectedGroup;
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size()==0){
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMobile("89993345223").withEmail("ii@relex.ru"));
        }
        app.goTo().groupPage();
        if (app.group().all().size()==0){
            app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
        }
        Groups groups = app.group().all();
        GroupData groupForDeletion = groups.iterator().next();
        System.out.println(groupForDeletion);
        selectedGroup = groupForDeletion.getName();

        app.goTo().homePage();
        app.contact().selectGroup(selectedGroup);
        if (app.contact().all().size()==0){
            app.contact().selectGroup("[all]");
            ContactData contactForAdded = app.contact().all().iterator().next();
            app.contact().addInGroup(contactForAdded, groupForDeletion);
        }
    }

    @Test
    public void testDeletionInGroup(){
        Contacts before = app.contact().all();
        ContactData contactForDeletion = before.iterator().next();
        System.out.println(contactForDeletion);
        app.contact().deleteFromGroup(contactForDeletion);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withOut(contactForDeletion)));
    }
}

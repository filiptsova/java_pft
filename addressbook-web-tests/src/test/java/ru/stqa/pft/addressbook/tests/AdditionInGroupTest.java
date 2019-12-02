package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdditionInGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions (){
        if (app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
        }
        if (app.db().contacts().size()==0){
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMobile("89993345223").withEmail("ii@relex.ru"));
        }
    }
    @Test
    public void testAdditionInGroup (){
        app.goTo().groupPage();
        Groups groups = app.group().all();
        app.goTo().homePage();
        Contacts contacts = app.contact().all();
        ContactData contactForAdded = null;
        Contacts before = null;
        GroupData groupToAdded = null;
        for (GroupData group : groups) {
            app.contact().selectGroup(group.getName());
            Contacts contactsInGroup = app.contact().all();
            for (ContactData contact : contacts) {
               if (!contactsInGroup.contains(contact)){
                   contactForAdded = contact;
                   groupToAdded = group;
                   before = contactsInGroup;
                   break;
               }
            }
        }
        System.out.println(contactForAdded);
        System.out.println(groupToAdded);
        if (groupToAdded == null){
            app.goTo().groupPage();
            groupToAdded = new GroupData().withName("test5").withFooter("test5").withHeader("test5");
            app.group().create(groupToAdded);
            contactForAdded = contacts.iterator().next();
            app.contact().selectGroup(groupToAdded.getName());
            before = app.contact().all();
        }
        app.contact().addInGroup(contactForAdded, groupToAdded);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contactForAdded)));
    }
}

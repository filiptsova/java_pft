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
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData contactForAdded = null;
        GroupData groupToAdded = null;
        Contacts before = null;
        for (GroupData group : groups) {
            Contacts contactsInGroup = app.db().contactsInGroup(group.getId());
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
            before = app.db().contactsInGroup(groupToAdded.getId());
        }
        app.contact().addInGroup(contactForAdded, groupToAdded);
        Contacts after = app.db().contactsInGroup(groupToAdded.getId());

        assertThat(after, equalTo(before.withAdded(contactForAdded)));
    }
}

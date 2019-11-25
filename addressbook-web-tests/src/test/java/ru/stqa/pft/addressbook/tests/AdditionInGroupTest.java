package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdditionInGroupTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions (){
        if (app.db().groups().size()==0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
        }
    }
    @Test
    public void testAdditionInGroup (){
        app.goTo().groupPage();
        Groups groups = app.group().all();
        GroupData groupToAdded = groups.iterator().next();
        System.out.println(groupToAdded);
        app.goTo().homePage();
        app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMobile("89993345223").withEmail("ii@relex.ru"));
        Contacts contacts = app.contact().all();
        ContactData contactForAdded = Collections.max(contacts, (c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
        app.contact().addInGroup(contactForAdded, groupToAdded);
        Contacts after = app.contact().all();
        assertThat(after, hasItem(contactForAdded));
    }
}

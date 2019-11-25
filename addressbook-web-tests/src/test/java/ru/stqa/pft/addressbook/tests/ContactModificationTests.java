package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod public void ensurePreconditions(){
        if (app.db().contacts().size()==0){
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMobile("89993345223").withEmail("ii@relex.ru"));
        }
    }
    @Test
    public void testContactModificationTests(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        System.out.println(modifiedContact);

        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Alex").withLastname("wZverev").withMobile("89993345223").withEmail("ii@relex.ru").withHome("22-22-22").withWork("33 33 33")
                .withEmail("ii@relex.ru").withEmail2("ii12@relex.ru").withEmail3("ii_pp@relex.ru").withAddress("438 DARK SPURT\n" + "SAN FRANCISCO\n" + "CA 94528\n" + "USA");
        app.goTo().homePage();
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }
}

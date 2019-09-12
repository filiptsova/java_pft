package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdditionTests extends TestBase {

  @Test
  public void testContactAdditionTests() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMobile("89993345223").withEmail("ii@relex.ru").withHome("22-22-22").withWork("33 33 33")
            .withEmail("ii@relex.ru").withEmail2("ii12@relex.ru").withEmail3("ii_pp@relex.ru").withAddress("438 DARK SPURT\n" + "SAN FRANCISCO\n" + "CA 94528\n" + "USA");
    app.contact().create(contact);
    Contacts after = (Contacts) app.contact().all();
    assertThat(after.size(),equalTo(before.size()+1));

    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

  }

}

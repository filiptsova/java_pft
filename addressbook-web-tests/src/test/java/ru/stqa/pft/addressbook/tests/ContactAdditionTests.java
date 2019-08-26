package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactAdditionTests extends TestBase {

  @Test
  public void testContactAdditionTests() throws Exception {
    app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "89993345223", "ii@relex.ru"));
  }

}

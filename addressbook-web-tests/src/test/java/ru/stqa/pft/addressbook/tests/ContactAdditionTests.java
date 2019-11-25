package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdditionTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null){
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
    return contacts.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validContactsFromJson")
  public void testContactAdditionTests(ContactData contact) throws Exception {
    Groups groups = app.db().groups();
    if (app.db().groups().size()==0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter("test2").withHeader("test3"));
    }
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    //File photo = new File ("src/test/resources/panoramnaya-fotografiya-66064.jpg");
    app.contact().create(contact);
    assertThat(app.contact().count(),equalTo(before.size()+1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
  }

}

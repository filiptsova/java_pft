package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions (){
        app.goTo().homePage();
        if (app.contact().all().size()==0){
            app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMobile("8(999)3345223").withHome("22-22-22").withWork("33 33 33")
                    .withEmail("ii@relex.ru").withEmail2("ii12@relex.ru").withEmail3("ii_pp@relex.ru").withAddress("438 DARK SPURT\n" + "SAN FRANCISCO\n" + "CA 94528\n" + "USA"));
        }
    }

    @Test
    public void testsForContacts(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditionForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String mergePhones (ContactData contact){
        return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork()).stream().filter((s)-> ! s.equals("")).map(ContactTests::cleanedOfPhone).collect(Collectors.joining("\n"));
    }

    private String mergeEmails (ContactData contact){
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream().filter((s)->! s.equals("")).map(ContactTests::cleanedOfEmail).collect(Collectors.joining("\n"));
    }

    public static String cleanedOfPhone(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public static String cleanedOfEmail(String email){
        return email.replaceAll("\\s", "");
    }
}


package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToHomePage() {
      click(By.linkText("home page"));
    }

    public void homePage(){
        click(By.linkText("home"));
    }

    public void submitContactAdd() {
      click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstname());
        type(By.name("lastname"),contactData.getLastname());
        type(By.name("work"), contactData.getWork());
        type(By.name("home"), contactData.getHome());
        type(By.name("mobile"),contactData.getMobile());
        type(By.name("email"),contactData.getEmail());
        type(By.name("email2"),contactData.getEmail2());
        type(By.name("email3"),contactData.getEmail3());
        type(By.name("address"),contactData.getAddress());
    }

    public void selectContactById(int id){
        driver.findElement(By.cssSelector("input[value='"+ id +"']")).click();
    }

    public void deleteSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContacts();
        confirmDeletion();
        homePage();
    }

    public void confirmDeletion() {
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("Окно для подтверждения удаления контакта не появилось");
        }
    }

    private void initContactModificationById(int id) {
        driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
        //driver.findElement(By.xpath("//a[@href='edit.php?id=204']")).click();
        System.out.println(id);
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact)
    {
        click(By.linkText("add new"));
        fillContactForm(contact);
        submitContactAdd();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {

        return driver.findElements(By.name("selected[]")).size();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        returnToHomePage();
    }


    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element:elements) {
            List<WebElement> tds = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(tds.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String lastname = tds.get(1).getText();
            String firstname = tds.get(2).getText();
            String allPhones = tds.get(5).getText();
            String allEmails = tds.get(4).getText();
            String address = tds.get(3).getText();
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return contacts;
    }

    public ContactData infoFromEditionForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastname = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        driver.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHome(home).withMobile(mobile).withWork(work).withEmail(email).withEmail2(email2).withEmail3(email3)
                .withAddress(address);
    }
}

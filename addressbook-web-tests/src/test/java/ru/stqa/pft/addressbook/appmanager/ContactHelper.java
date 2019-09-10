package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.ArrayList;
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
      type(By.name("mobile"),contactData.getMobile());
      type(By.name("email"),contactData.getEmail());
    }

    public void selectContact(int index) {

        driver.findElements(By.name("selected[]")).get(index).click();
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

    public void delete(int index) {
        selectContact(index);
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

    public void initContactModification(int id) {
        driver.findElement(By.xpath("//a[@href='edit.php?id=" + id +"']")).click();
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
        initContactModification(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        returnToHomePage();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element:elements) {
            List<WebElement> tds = element.findElements(By.tagName("td"));
            String lastname = tds.get(1).getText();
            String firstname = tds.get(2).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
}

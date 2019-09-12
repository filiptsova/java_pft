package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.checkerframework.checker.units.qual.C;

import java.util.Objects;

public class ContactData {
    private  int id = Integer.MAX_VALUE;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String mobile;
    private String home;
    private String work;
    private String allPhones;
    @Expose
    private String email;
    private String email2;
    private String email3;
    private String allEmails;
    private String address;

    public int getId() { return id; }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getHome() { return home; }

    public ContactData withHome(String home) {
        this.home = home;
        return this;
    }

    public String getWork() { return work; }

    public ContactData withWork(String work) {
        this.work = work;
        return this;
    }

    public String getAllPhones() { return allPhones; }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getEmail2() { return email2; }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() { return email3; }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getAllEmails() { return allEmails; }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getAddress() { return address; }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public String getFirstname() { return firstname; }

    public String getLastname() { return lastname; }

    public String getMobile() { return mobile; }

    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }
}

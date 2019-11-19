package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import jdk.internal.instrumentation.TypeMapping;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name = "addressbook")

public class ContactData {
    @Id
    @Column(name = "id")
    private  int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstname;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;

    @Column(name = "home")
    @Type(type = "text")
    private String home;

    @Column(name = "work")
    @Type(type = "text")
    private String work;

    @Transient
    private String allPhones;

    @Expose
    @Column(name = "email")
    @Type(type = "text")

    private String email;
    @Column(name = "email2")
    @Type(type = "text")

    private String email2;
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String allEmails;

    @Column(name = "address")
    @Type(type = "text")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(mobile, that.mobile) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, mobile, email);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

}

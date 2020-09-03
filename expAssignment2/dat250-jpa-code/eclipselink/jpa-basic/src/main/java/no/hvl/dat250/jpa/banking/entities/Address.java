package no.hvl.dat250.jpa.banking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private int id;
    private String street;
    private int Number;
    private int postalnumber;
    private String city;

    @OneToOne
    private Person person;

    public Address() {

    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalNumber(int postalnumber) {
        this.postalnumber = postalnumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                ", street='" + street + '\'' +
                ", Number=" + Number +
                ", postalnumnber=" + postalnumber +
                ", city='" + city +
                '}';
    }
}



package no.hvl.dat250.jpa.banking.entities;


import javax.persistence.*;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToOne
    @JoinColumn(name = "card_id")
    private CreditCard creditCard;

    @OneToOne
    @JoinColumn(name = "address_fk")
    private Address address;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                ", name='" + name + '\'' +
                ", creditCard=" + creditCard +
                ", address=" + address +
                '}';
    }
}

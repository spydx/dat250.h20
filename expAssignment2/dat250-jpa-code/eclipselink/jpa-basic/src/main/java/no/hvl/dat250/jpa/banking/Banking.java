package no.hvl.dat250.jpa.banking;

import no.hvl.dat250.jpa.banking.entities.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Banking {
    private static EntityManagerFactory factory;
    private static final String PERSISTENCE_NAME = "banking";

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
        EntityManager em = factory.createEntityManager();


        em.getTransaction().begin();
        Person p = new Person();
        p.setName("Kenneth");
        em.persist(p);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Bank bank = new Bank();
        bank.setName("American Express");
        em.persist(bank);
        em.getTransaction().commit();

        em.getTransaction().begin();
        PinCode pin = new PinCode();
        pin.setPincode(1234);
        em.persist(pin);
        em.getTransaction().commit();

        em.getTransaction().begin();
        CreditCard cc = new CreditCard();
        cc.setBalance(20000);
        cc.setBank(bank);
        cc.setPinCode(pin);
        em.persist(cc);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Address adr = new Address();
        adr.setStreet("Neumanns gate");
        adr.setNumber(12);
        adr.setPostalNumber(5015);
        adr.setCity("Bergen");
        em.persist(adr);
        p.setAddress(adr);
        p.setCreditCard(cc);
        em.getTransaction().commit();

        System.out.println(p.toString());

    }
}

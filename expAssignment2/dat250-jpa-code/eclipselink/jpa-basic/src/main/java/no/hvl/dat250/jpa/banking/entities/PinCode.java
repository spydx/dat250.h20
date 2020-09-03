package no.hvl.dat250.jpa.banking.entities;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.Random;

@Entity
public class PinCode {

    @Id
    @GeneratedValue
    private int id;
    private int pincode;
    private int count;



    public PinCode() {

    }

    public int getCount() {
        return count;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public Boolean validatePin(int pin) {
        return pin == pincode;
    }
}

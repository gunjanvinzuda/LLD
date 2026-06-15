package org.example;

public class Card {
    private final String number;
    private String pin;

    public Card(String number, String pin){
        this.number = number;
        this.pin = pin;
    }

    public void setPin(String pin){
        this.pin = pin;
    }

    public String getNumber(){
        return this.number;
    }

    public boolean validatePin(String pin){
        return this.pin.equals(pin);
    }

}

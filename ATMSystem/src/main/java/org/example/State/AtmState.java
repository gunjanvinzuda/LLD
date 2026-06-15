package org.example.State;

import org.example.AtmOperation;
import org.example.AtmSystem;

public abstract class AtmState {
    //insert card
    public void insertCard(AtmSystem atm, String cardNumber){
        throw new IllegalStateException("AtmState: insertCrd(): Insertion of card not allowed.");
    }

    //authenticate card
    public void authenticateCard(AtmSystem atm, String cardPin){
        throw new IllegalStateException();
    }

    //select operation
    public void selectOperation(AtmSystem atm, AtmOperation operation){
        throw new IllegalStateException();
    }

    //check balance
    public void checkBalance(AtmSystem atm){
        throw new IllegalStateException();
    }

    //withdraw money
    public void withdrawMoney(AtmSystem atm, int amount){
        throw new IllegalStateException();
    }

    //eject card
    public void ejectCard(AtmSystem atm){
        throw new IllegalStateException();
    }
}

package org.example.State;

import org.example.AtmOperation;
import org.example.AtmSystem;

public abstract class AtmState {
    //insert card
    public void insertCard(AtmSystem atm, String cardNumber){
        throw new IllegalStateException("AtmState: insertCard(): Insertion of card not allowed.");
    }

    //authenticate card
    public void authenticateCard(AtmSystem atm, String cardPin){
        throw new IllegalStateException("AtmState: authenticateCard(): Authentication of card not allowed.");
    }

    //select operation
    public void selectOperation(AtmSystem atm, AtmOperation operation){
        throw new IllegalStateException("AtmState: selectOperation(): Operation selection not allowed.");
    }

    //check balance
    public void checkBalance(AtmSystem atm){
        throw new IllegalStateException("AtmState: checkBalance(): Checking balance not allowed.");
    }

    //withdraw money
    public void withdrawMoney(AtmSystem atm, int amount){
        throw new IllegalStateException("AtmState: withdrawMoney(): Withdrawing money not allowed.");
    }

    //eject card
    public void ejectCard(AtmSystem atm){
        throw new IllegalStateException("AtmState: ejectCard(): Ejecting card not allowed.");
    }
}

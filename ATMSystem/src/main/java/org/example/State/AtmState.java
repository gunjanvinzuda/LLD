package org.example.State;

import org.example.AtmOperation;
import org.example.AtmSystem;

public abstract class AtmState {

    AtmSystem atm;

    AtmState(AtmSystem atm){
        this.atm = atm;
    }
    //insert card
    public void insertCard(String cardNumber){
        throw new IllegalStateException("AtmState: insertCard(): Insertion of card not allowed.");
    }

    //authenticate card
    public void authenticateCard(String cardPin){
        throw new IllegalStateException("AtmState: authenticateCard(): Authentication of card not allowed.");
    }

    //select operation
    public void selectOperation(AtmOperation operation){
        throw new IllegalStateException("AtmState: selectOperation(): Operation selection not allowed.");
    }

    //check balance
    public void checkBalance(){
        throw new IllegalStateException("AtmState: checkBalance(): Checking balance not allowed.");
    }

    //withdraw money
    public void withdrawMoney(int amount){
        throw new IllegalStateException("AtmState: withdrawMoney(): Withdrawing money not allowed.");
    }

    //eject card
    public void ejectCard(){
        throw new IllegalStateException("AtmState: ejectCard(): Ejecting card not allowed.");
    }
}

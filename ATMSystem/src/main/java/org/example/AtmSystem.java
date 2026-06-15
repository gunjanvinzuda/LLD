package org.example;

import org.example.ChainOfReposnsibility.DispenserChain;
import org.example.ChainOfReposnsibility.NoteDispenser100;
import org.example.ChainOfReposnsibility.NoteDispenser500;
import org.example.State.AtmState;
import org.example.State.IdleState;

public class AtmSystem {
    private BankService bankService;
    private AtmState state;
    private Card card;
    private DispenserChain dispenserChain;

    public AtmSystem(BankService service){
        this.bankService = service;
        this.card = null;
        this.state = new IdleState();
        dispenserChain = new NoteDispenser500(new NoteDispenser100(null, 50), 50);
    }


    public void readCard(String cardNumber){
        this.card = bankService.getCard(cardNumber);

        if(card == null)
            throw new IllegalArgumentException("AtmSystem: readCard(): Invalid card number.");
    }

    public void setState(AtmState state) {
        this.state = state;
    }

    public AtmState getState(){
        return state;
    }

    public boolean validatePin(String pin){
        return this.card.validatePin(pin);
    }

    public void ejectCard(){
        this.card = null;
    }

    public double getBalance(){
        return bankService.getBalance(card);
    }

    public DispenserChain getChain(){
        return dispenserChain;
    }

    public void deductAmount(int amount){
        bankService.deductAmount(card, amount);
    }
}

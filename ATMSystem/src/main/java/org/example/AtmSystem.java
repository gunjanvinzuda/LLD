package org.example;

import org.example.ChainOfResponsibility.DispenserChain;
import org.example.ChainOfResponsibility.NoteDispenser100;
import org.example.ChainOfResponsibility.NoteDispenser500;
import org.example.State.AtmState;
import org.example.State.IdleState;
import java.math.BigDecimal;

public class AtmSystem {
    private final BankService bankService;
    private AtmState state;
    private Card card;
    private final DispenserChain dispenserChain;
    private static final int INITIAL_500_NOTE_COUNT = 50;
    private static final int INITIAL_100_NOTE_COUNT = 50;

    public AtmSystem(BankService service){
        this.bankService = service;
        this.card = null;
        this.state = new IdleState();
        dispenserChain = new NoteDispenser500(new NoteDispenser100(null, INITIAL_100_NOTE_COUNT), INITIAL_500_NOTE_COUNT);
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
        if(this.card == null) throw new IllegalStateException("AtmSystem: validatePin(): No card present.");
        return this.card.validatePin(pin);
    }

    public void ejectCard(){
        this.card = null;
    }

    public BigDecimal getBalance(){
        if(this.card == null) throw new IllegalStateException("AtmSystem: getBalance(): No card present.");
        return bankService.getBalance(card);
    }

    public DispenserChain getChain(){
        return dispenserChain;
    }

    public void deductAmount(int amount){
        if(this.card == null) throw new IllegalStateException("AtmSystem: deductAmount(): No card present.");
        bankService.deductAmount(card, amount);
    }
}

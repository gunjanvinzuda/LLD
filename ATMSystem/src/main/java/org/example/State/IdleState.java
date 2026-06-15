package org.example.State;

import org.example.AtmSystem;

public class IdleState extends AtmState{
    @Override
    public void insertCard(AtmSystem atm, String cardNumber) {
        try{
            atm.readCard(cardNumber);
            atm.setState(new HasCardState());
        }catch (Exception e){
            System.out.println("IdleState: insertCard(): " + e.getMessage());
        }
    }
}

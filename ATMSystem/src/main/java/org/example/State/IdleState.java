package org.example.State;

import org.example.AtmSystem;

public class IdleState extends AtmState{

    IdleState(AtmSystem atmSystem){
        super(atmSystem);
    }

    @Override
    public void insertCard(String cardNumber) {
        try{
            atm.readCard(cardNumber);
            atm.setState(new HasCardState(atm));
        }catch (Exception e){
            System.out.println("IdleState: insertCard(): " + e.getMessage());
        }
    }
}

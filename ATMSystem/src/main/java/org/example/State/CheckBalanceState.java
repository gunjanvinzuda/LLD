package org.example.State;

import org.example.AtmSystem;

public class CheckBalanceState extends AtmState{

    CheckBalanceState(AtmSystem atmSystem){
        super(atmSystem);
    }

    @Override
    public void checkBalance() {
        System.out.println("The current balance is: "+atm.getBalance().toPlainString());
        atm.setState(new ExitState(atm));
    }
}

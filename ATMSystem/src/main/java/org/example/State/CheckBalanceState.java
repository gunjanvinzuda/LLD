package org.example.State;

import org.example.AtmSystem;

public class CheckBalanceState extends AtmState{

    @Override
    public void checkBalance(AtmSystem atm) {
        System.out.println("The current balance is: "+atm.getBalance());
        atm.setState(new ExitState());
    }
}

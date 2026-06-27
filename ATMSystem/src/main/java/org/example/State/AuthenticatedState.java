package org.example.State;

import org.example.AtmOperation;
import org.example.AtmSystem;

public class AuthenticatedState extends AtmState{

    AuthenticatedState(AtmSystem atm){
        super(atm);
    }

    @Override
    public void selectOperation(AtmOperation operation) {
        switch (operation){
            case CHECK_BALANCE -> atm.setState(new CheckBalanceState(atm));
            case WITHDRAW -> atm.setState(new WithdrawState(atm));
            default -> atm.setState(new ExitState(atm));
        }

    }
}

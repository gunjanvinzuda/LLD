package org.example.State;

import org.example.AtmOperation;
import org.example.AtmSystem;

public class AuthenticatedState extends AtmState{

    @Override
    public void selectOperation(AtmSystem atm, AtmOperation operation) {
        switch (operation){
            case CHECK_BALANCE -> atm.setState(new CheckBalanceState());
            case WITHDRAW -> atm.setState(new WithdrawState());
            default -> atm.setState(new ExitState());
        }

    }
}

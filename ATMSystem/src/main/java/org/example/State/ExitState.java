package org.example.State;

import org.example.AtmSystem;

public class ExitState extends AtmState{
    @Override
    public void ejectCard(AtmSystem atm) {
        atm.ejectCard();
        atm.setState(new IdleState());
    }
}

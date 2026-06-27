package org.example.State;

import org.example.AtmSystem;

public class ExitState extends AtmState{

    ExitState(AtmSystem atmSystem){
        super(atmSystem);
    }
    @Override
    public void ejectCard() {
        atm.ejectCard();
        atm.setState(new IdleState(atm));
    }
}

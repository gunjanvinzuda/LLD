package org.example.State;

import org.example.AtmSystem;

public class HasCardState extends AtmState{
    @Override
    public void authenticateCard(AtmSystem atm, String pin) {
        if(atm.validatePin(pin)){
            atm.setState(new AuthenticatedState());
        }else{
            System.out.println("HasCardState: authenticateCard(): Card authentication failed.");
            atm.setState(new ExitState());
        }
    }
}

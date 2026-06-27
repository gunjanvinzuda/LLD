package org.example.State;

import org.example.AtmSystem;

public class HasCardState extends AtmState{
    private int attempts = 0;
    private static final int MAX_ATTEMPTS = 3;

    HasCardState(AtmSystem atmSystem){
        super(atmSystem);
    }

    @Override
    public void authenticateCard(String pin) {
        if(atm.validatePin(pin)){
            atm.setState(new AuthenticatedState(atm));
        }else{
            attempts++;
            if(attempts < MAX_ATTEMPTS) {
                System.out.println("HasCardState: authenticateCard(): Card authentication failed. Attempts remaining: " + (MAX_ATTEMPTS - attempts));
            }else{
                System.out.println("HasCardState: authenticateCard(): Maximum authentication attempts exceeded. Card will be ejected.");
                atm.ejectCard();
                atm.setState(new ExitState(atm));
            }
        }
    }
}

package org.example.State;

import org.example.AtmSystem;
import org.example.ChainOfReposnsibility.DispenserChain;

public class WithdrawState extends AtmState{
    @Override
    public void withdrawMoney(AtmSystem atm, int amount) {
        DispenserChain chain = atm.getChain();
        double balance = atm.getBalance();

        if(amount <= balance && chain.canDispense(amount)){
           try{
               chain.dispenseNotes(amount);
               atm.deductAmount(amount);
           }catch (Exception e){
               System.out.println("WithdrawState: withdrawMoney(): "+e.getMessage());
           }
        }else{
            //TODO:
            System.out.println("WithdrawState: withdrawMoney(): Request cannot be served.");
        }
        atm.setState(new ExitState());
    }
}

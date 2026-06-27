package org.example.State;

import org.example.AtmSystem;
import org.example.ChainOfResponsibility.DispenserChain;
import java.math.BigDecimal;

public class WithdrawState extends AtmState{
    WithdrawState(AtmSystem atm) {
        super(atm);
    }

    @Override
    public void withdrawMoney(int amount) {
        if(amount <= 0) {
            System.out.println("WithdrawState: withdrawMoney(): Invalid amount. Amount must be greater than 0.");
            atm.setState(new ExitState(atm));
            return;
        }

        DispenserChain chain = atm.getChain();
        BigDecimal balance = atm.getBalance();

        if(balance.compareTo(BigDecimal.valueOf(amount)) >= 0 && chain.canDispense(amount)){
           try{
               chain.dispenseNotes(amount);
               atm.deductAmount(amount);
           }catch (Exception e){
               System.out.println("WithdrawState: withdrawMoney(): "+e.getMessage());
           }
        }else{
            System.out.println("WithdrawState: withdrawMoney(): Request cannot be served.");
        }
        atm.setState(new ExitState(atm));
    }
}

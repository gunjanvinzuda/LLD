package org.example.State;

import org.example.Denomination;
import org.example.VendingSystem;

public class HasMoneyState implements MachineState{
    private final VendingSystem system;

    public HasMoneyState(VendingSystem system){
        this.system = system;
    }

    @Override
    public void selectProduct(String code) {
        System.out.println("HasMoneyState: selectProduct(): Product already selected.");
    }

    @Override
    public void insertMoney(Denomination d, int count) {
        System.out.println("HasMoneyState: processTransaction(): Enough amount received already");
    }

    @Override
    public void processTransaction() {
        double price = system.getInventory().getShelf(system.getSelectedProductCode()).getPrice();
        boolean canProcessChange = system.getVault().processChange(price);

        if(canProcessChange){
            system.setCurrentState(system.dispenseState);
        }else{
            System.out.println("HasMoneyState: dispenseProduct(): Cannot process change.");
            cancelTransaction();
            system.setCurrentState(system.idleState);
        }
    }

    @Override
    public void dispenseProduct() {
        System.out.println("HasMoneyState: dispenseProduct(): No product selected.");
    }

    @Override
    public void cancelTransaction() {
        system.getVault().rollBackTransaction();
        System.out.println("HasMoneyState: dispenseProduct(): Transaction canceled.");
    }
}

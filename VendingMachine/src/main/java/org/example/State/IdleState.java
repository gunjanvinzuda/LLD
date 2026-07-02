package org.example.State;

import org.example.Denomination;
import org.example.VendingSystem;

public class IdleState implements MachineState{
    private final VendingSystem system;

    public IdleState(VendingSystem system){
        this.system = system;
    }

    @Override
    public void selectProduct(String code) {
        if(!system.getInventory().isAvailable(code)){
            //throw new IllegalArgumentException("IdleState: insertMoney(): product not available.");
            System.out.println("IdleState: insertMoney(): Product not available.");
            return;
        }
        system.setSelectedProductCode(code);
        system.setCurrentState(system.productSelectedState);
    }

    @Override
    public void insertMoney(Denomination d, int count) {
        System.out.println("IdleState: insertMoney(): Please select the product first.");
    }

    @Override
    public void processTransaction() {
        System.out.println("IdleState: processTransaction(): No transaction to process.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("IdleState: dispenseProduct(): No product selected.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("IdleState: dispenseProduct(): No transaction to cancel.");
    }
}

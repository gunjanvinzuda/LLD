package org.example.State;

import org.example.Denomination;
import org.example.VendingSystem;

public class ProductSelectedState implements MachineState{
    private final VendingSystem system;

    public ProductSelectedState(VendingSystem system){
        this.system = system;
    }

    @Override
    public void selectProduct(String code) {
        System.out.println("ProductSelectedState: selectProduct(): Product already selected.");
    }

    @Override
    public void insertMoney(Denomination d, int count) {
        system.getVault().insertMoney(d, count);
        double total = system.getVault().getInsertedTotal();
        double price = system.getInventory().getShelf(system.getSelectedProductCode()).getPrice();

        if(total >= price){
            System.out.println("ProductSelectedState: insertMoney: Sufficient amount received.");
            system.setCurrentState(system.hasMoneyState);
        }
    }

    @Override
    public void processTransaction() {
        System.out.println("ProductSelectedState: processTransaction(): Please insert enough amount.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("ProductSelectedState: dispenseProduct(): Please insert enough amount.");
    }

    @Override
    public void cancelTransaction() {
        System.out.println("ProductSelectedState: dispenseProduct(): No transaction to cancel.");
    }
}

package org.example.State;

import org.example.Denomination;
import org.example.Shelf;
import org.example.VendingSystem;

import java.util.Optional;

public class DispenseState implements MachineState{
    private final VendingSystem system;

    public DispenseState(VendingSystem system){
        this.system = system;
    }

    @Override
    public void selectProduct(String code) {
        System.out.println("DispenseState: selectProduct(): Product already selected.");
    }

    @Override
    public void insertMoney(Denomination d, int count) {
        System.out.println("DispenseState: processTransaction(): Enough amount received already");
    }

    @Override
    public void processTransaction() {
        System.out.println("DispenseState: dispenseProduct(): Transaction processes already.");
    }

    @Override
    public void dispenseProduct() {
        Shelf shelf = system.getInventory().getShelf(system.getSelectedProductCode());
        shelf.updateQuantity(-1);
        System.out.println("DispenseState: dispenseProduct(): Item dispensed.");
        system.setCurrentState(system.idleState);
    }

    @Override
    public void cancelTransaction() {
        System.out.println("DispenseState: dispenseProduct(): Cannot cancel the transaction.");
    }
}


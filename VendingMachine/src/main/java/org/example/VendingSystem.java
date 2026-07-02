package org.example;

import org.example.State.*;

public class VendingSystem {
    private ChangeVault vault = new ChangeVault();
    private Inventory inventory = new Inventory();
    private String selectedProductCode = null;
    private MachineState currentState = null;

    public final MachineState idleState = new IdleState(this);
    public final MachineState productSelectedState = new ProductSelectedState(this);
    public final MachineState hasMoneyState = new HasMoneyState(this);
    public final MachineState dispenseState = new DispenseState(this);

    public VendingSystem(){
        this.currentState = idleState;
    }

    public void setSelectedProductCode(String code) {
        this.selectedProductCode = code;
    }

    public void setCurrentState(MachineState currentState) {
        this.currentState = currentState;
    }

    public String getSelectedProductCode() {
        return selectedProductCode;
    }

    public MachineState getCurrentState() {
        return currentState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ChangeVault getVault() {
        return vault;
    }
}

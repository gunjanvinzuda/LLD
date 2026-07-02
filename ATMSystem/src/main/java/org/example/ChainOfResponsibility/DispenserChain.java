package org.example.ChainOfResponsibility;

public interface DispenserChain {
    boolean canDispense(int amount);
    void dispenseNotes(int amount);
}

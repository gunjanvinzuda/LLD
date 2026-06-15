package org.example.ChainOfReposnsibility;

public interface DispenserChain {
    boolean canDispense(int amount);
    void dispenseNotes(int amount);
}

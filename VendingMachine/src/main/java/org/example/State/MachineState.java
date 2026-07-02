package org.example.State;

import org.example.Denomination;

public interface MachineState {
    void selectProduct(String code);
    void insertMoney(Denomination d, int count);
    void processTransaction();
    void dispenseProduct();
    void cancelTransaction();
}

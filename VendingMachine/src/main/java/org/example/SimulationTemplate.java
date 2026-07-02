package org.example;

public class SimulationTemplate {
    private VendingSystem system;

    public SimulationTemplate(){
        system = new VendingSystem();
        Inventory inventory = system.getInventory();

        inventory.addShelve(new Shelf("A1", "Coke", 70, 5));
        inventory.addShelve(new Shelf("B1", "Lay's", 40, 2));
        inventory.addShelve(new Shelf("C1", "Pepsi", 70, 5));
        inventory.addShelve(new Shelf("D1", "Chips", 30, 0));

        ChangeVault vault = system.getVault();

        vault.addToVault(Denomination.TEN, 2);
        run();
    }

    private void run(){
        happyFlow();
        itemNotAvailableFlow();
        itemSoldOutFlow();
        noChangeAvailableFlow();
    }

    private void happyFlow(){
        System.out.println("=========== happyFlow START ===========");
        system.getCurrentState().selectProduct("B1");
        system.getCurrentState().insertMoney(Denomination.FIFTY, 1);
        system.getCurrentState().processTransaction();
        system.getCurrentState().dispenseProduct();
        System.out.println("=========== happyFlow END ===========");
    }

    private void itemNotAvailableFlow(){
        System.out.println("=========== itemNotAvailableFlow START ===========");
        system.getCurrentState().selectProduct("D1");
        system.getCurrentState().insertMoney(Denomination.FIFTY, 2);
        system.getCurrentState().processTransaction();
        system.getCurrentState().dispenseProduct();
        System.out.println("=========== noChangeAvailableFlow END ===========");
    }

    private void itemSoldOutFlow(){
        System.out.println("=========== itemSoldOutFlow START ===========");
        system.getCurrentState().selectProduct("B1");
        system.getCurrentState().insertMoney(Denomination.FIFTY, 1);//10
        system.getCurrentState().processTransaction();
        system.getCurrentState().dispenseProduct();

        System.out.println("=========== noChangeAvailableFlow MIDWAY ===========");

        system.getCurrentState().selectProduct("B1");
        system.getCurrentState().insertMoney(Denomination.FIFTY, 1);
        system.getCurrentState().processTransaction();
        system.getCurrentState().dispenseProduct();
        System.out.println("=========== noChangeAvailableFlow END ===========");
    }

    private void noChangeAvailableFlow(){
        System.out.println("=========== noChangeAvailableFlow START ===========");
        system.getCurrentState().selectProduct("A1");
        system.getCurrentState().insertMoney(Denomination.FIFTY, 2);
        system.getCurrentState().processTransaction();
        system.getCurrentState().dispenseProduct();
        System.out.println("=========== noChangeAvailableFlow END ===========");
    }

}

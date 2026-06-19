package org.example;

public class Main {
    public static void main(String[] args) {
       Card card = new Card("123456789012", "1234");
       BankService bankService = new BankService();
       bankService.addCard(card, 10000);

       AtmSystem system = new AtmSystem(bankService);

       //Check balance
       system.getState().insertCard(system, "123456789012");
       system.getState().authenticateCard(system, "1534");//Wrong pin
       system.getState().authenticateCard(system, "1234");
       system.getState().selectOperation(system, AtmOperation.CHECK_BALANCE);
       system.getState().checkBalance(system);
       system.getState().ejectCard(system);

       //Withdraw money
        system.getState().insertCard(system, "123456789012");
        system.getState().authenticateCard(system, "1234");
        system.getState().selectOperation(system, AtmOperation.WITHDRAW);
        system.getState().withdrawMoney(system, 9000);
        system.getState().ejectCard(system);

        //Check balance
        system.getState().insertCard(system, "123456789012");
        system.getState().authenticateCard(system, "1234");
        system.getState().selectOperation(system, AtmOperation.CHECK_BALANCE);
        system.getState().checkBalance(system);
        system.getState().ejectCard(system);

    }
}

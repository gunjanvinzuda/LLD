package org.example;

public class Main {
    public static void main(String[] args) {
       Card card = new Card("123456789012", "1234");
       BankService bankService = new BankService();
       bankService.addCard(card, 10000);

       AtmSystem system = new AtmSystem(bankService);

       //Check balance
       system.getState().insertCard( "123456789012");
       system.getState().authenticateCard( "1534");//Wrong pin
       system.getState().authenticateCard( "1234");
       system.getState().selectOperation( AtmOperation.CHECK_BALANCE);
       system.getState().checkBalance();
       system.getState().ejectCard();

       //Withdraw money
        system.getState().insertCard( "123456789012");
        system.getState().authenticateCard( "1234");
        system.getState().selectOperation( AtmOperation.WITHDRAW);
        system.getState().withdrawMoney( 9000);
        system.getState().ejectCard();

        //Check balance
        system.getState().insertCard( "123456789012");
        system.getState().authenticateCard( "1234");
        system.getState().selectOperation( AtmOperation.CHECK_BALANCE);
        system.getState().checkBalance();
        system.getState().ejectCard();

    }
}

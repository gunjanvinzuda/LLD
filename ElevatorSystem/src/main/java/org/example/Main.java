package org.example;

public class Main {

    private static void simulateTime(int time){
        try{
            Thread.sleep(time);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    static void main(String[] args){
        ElevatorSystem system = ElevatorSystem.getINSTANCE(2, new NearestElevatorStrategy());
        system.start();

        Elevator elevator8 = system.submitRequest(8, ElevatorDirection.UP);
        simulateTime(100);
        Elevator elevator3 = system.submitRequest(3, ElevatorDirection.UP);
        simulateTime(8000);

        elevator3.pressFloorButton(10);
        simulateTime(100);
        elevator8.pressFloorButton(2);

        system.shutdownSystem();
    }
}

/*

Elevator 1 is started.
Elevator 2 is started.
Elevator 2 is accepted request 8
Elevator 2 is going UP.
Elevator 2 is accepted request 3
Elevator 2 is going UP.
Elevator 2 is going UP.
Elevator 2 is stopping at floor 3
Elevator 2 is going UP.
Elevator 2 is going UP.
Elevator 2 is accepted request 10
Elevator 2 is going UP.
Elevator 2 is accepted request 2
Elevator 2 is going UP.
Elevator 2 is going UP.
Elevator 2 is stopping at floor 8
Elevator 2 is going UP.
Elevator 2 is going UP.
Elevator 2 is stopping at floor 10


 */
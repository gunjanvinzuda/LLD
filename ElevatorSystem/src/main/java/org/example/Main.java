package org.example;

public class Main {

    private static void simulateTime(int time){
        try{
            Thread.sleep(time);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args){
        ElevatorSystem system = ElevatorSystem.getInstance();
        system.init(2, new NearestElevatorStrategy());
        system.start();

        int elevator8 = system.submitRequest(8, ElevatorDirection.UP);
        simulateTime(100);
        int elevator3 = system.submitRequest(3, ElevatorDirection.UP);
        simulateTime(3600);

        system.pressFloorButton(elevator3, 10);
        simulateTime(6000);
        system.pressFloorButton(elevator8, 2);

        system.shutdownSystem();
    }
}
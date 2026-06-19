package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ElevatorSystem {
    private List<Elevator> elevators;
    private ElevatorSelectionStrategy selectionStrategy;
    private ExecutorService executorService;
    private static volatile ElevatorSystem INSTANCE = new ElevatorSystem();

    private ElevatorSystem(){
        this.elevators = new ArrayList<>();
    }

    public void init(int numElevators, ElevatorSelectionStrategy strategy){
        this.executorService = Executors.newFixedThreadPool(numElevators);
        this.selectionStrategy = strategy;

        for(int i=1; i<=numElevators; i++) {
            Elevator elevator = new Elevator(i);
            elevators.add(elevator);
        }
        System.out.println("System initiallized.");
    }

    public static ElevatorSystem getInstance(){
        return INSTANCE;
    }

    public int submitRequest(int reqFloor, ElevatorDirection reqDirection){
        Elevator elevator = selectionStrategy.selectElevator(elevators, reqFloor, reqDirection);
        elevator.addRequest(reqFloor);
        return elevator.getId();
    }

    public void pressFloorButton(int elevatorId, int destFloor){
        elevators.get(elevatorId-1).addRequest(destFloor);
    }

    public void start(){
        for(Elevator elevator: elevators){
            executorService.submit(elevator);
        }
    }

    public void waitUntilIdle(){
        while (true) {
            boolean busy = false;
            for (Elevator elevator : elevators) {
                if (!elevator.isIdle()) {
                    busy = true;
                    break;
                }
            }
            if (!busy) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void shutdownSystem(){
        System.out.println("Shutting down the system.");
        for(Elevator elevator: elevators) elevator.shutdown();

        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

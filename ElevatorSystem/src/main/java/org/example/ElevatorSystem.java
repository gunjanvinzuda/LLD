package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ElevatorSystem {
    List<Elevator> elevators;
    ElevatorSelectionStrategy selectionStrategy;
    ExecutorService executorService;
    private static volatile ElevatorSystem INSTANCE = null;

    private ElevatorSystem(int numElevators, ElevatorSelectionStrategy strategy){
        this.executorService = Executors.newFixedThreadPool(numElevators);
        this.selectionStrategy = strategy;

        this.elevators = new ArrayList<>();
        for(int i=1; i<=numElevators; i++) {
            Elevator elevator = new Elevator(i);
            elevators.add(elevator);
        }
    }

    public static ElevatorSystem getINSTANCE(int numElevators, ElevatorSelectionStrategy strategy){
        if(INSTANCE == null){
            synchronized (ElevatorSystem.class){
                if(INSTANCE == null){
                    INSTANCE = new ElevatorSystem(numElevators, strategy);
                }
            }
        }
        return INSTANCE;
    }

    public Elevator submitRequest(int reqFloor, ElevatorDirection reqDirection){
        Elevator elevator = selectionStrategy.selectElevator(elevators, reqFloor, reqDirection);
        elevator.addRequest(reqFloor);
        return elevator;
    }

    public void start(){
        for(Elevator elevator: elevators){
            executorService.submit(elevator);
        }
    }

    public void shutdownSystem(){
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

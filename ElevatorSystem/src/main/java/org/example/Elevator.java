package org.example;

import java.util.TreeSet;

public class Elevator implements Runnable {
    private final int id;
    private ElevatorDirection direction;
    private int currentFloor;
    private TreeSet<Integer> requests;
    private boolean active;

    Elevator(int id){
        this.id = id;
        this.currentFloor = 0;
        this.direction = ElevatorDirection.IDLE;
        this.active = true;
        requests = new TreeSet<>();

    }


    public int getCurrentFloor(){
        return this.currentFloor;
    }

    public ElevatorDirection getDirection(){
        return this.direction;
    }

    public void addRequest(int floor){
        System.out.println("Elevator " + id + " has accepted request " + floor);
        this.requests.add(floor);
    }

    @Override
    public void run(){
        System.out.println("Elevator " + id + " is started.");
        while(active){
            if(requests.isEmpty()){
                try{
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }
                continue;
            }

            //System.out.println("Elevator " + id + " is running.");
            if(direction == ElevatorDirection.IDLE || direction == ElevatorDirection.UP){
                Integer nextUpStop = requests.higher(currentFloor);

                if(nextUpStop != null){
                    direction = ElevatorDirection.UP;
                    System.out.println("Elevator " + id + " is going UP.");
                    currentFloor++;
                    simulateMove();

                    if(requests.remove(currentFloor)){
                        simulateDoor();
                        System.out.println("Elevator " + id + " is stopping at floor " + currentFloor);
                    }
                }else{
                    direction = requests.lower(currentFloor) == null ? ElevatorDirection.IDLE : ElevatorDirection.DOWN;
                }
            }else if(direction == ElevatorDirection.DOWN){
                Integer nextDownStop = requests.lower(currentFloor);

                if(nextDownStop != null){
                    direction = ElevatorDirection.DOWN;
                    System.out.println("Elevator " + id + " is going DOWN.");
                    currentFloor--;
                    simulateMove();

                    if(requests.remove(currentFloor)){
                        simulateDoor();
                        System.out.println("Elevator " + id + " is stopping at floor " + currentFloor);
                    }
                }else{
                    direction = requests.higher(currentFloor) == null ? ElevatorDirection.IDLE : ElevatorDirection.UP;
                }
            }
        }
    }

    private void simulateMove(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void simulateDoor(){
        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public void pressFloorButton(int destFloor){
        addRequest(destFloor);
    }

    public void shutdown(){
        this.active = false;
    }
}
package org.example;

import java.util.List;

public class NearestElevatorStrategy implements ElevatorSelectionStrategy{
    @Override
    public Elevator selectElevator(List<Elevator> elevators, int reqFloor, ElevatorDirection reqDirection) {
        Elevator bestElevator = null;
        int minDist = 999999;

        for(Elevator elevator: elevators){
            if(canServe(elevator, reqFloor, reqDirection)){
                int dist = Math.abs(reqFloor - elevator.getCurrentFloor());
                if(dist < minDist) bestElevator = elevator;
            }
        }

        if(bestElevator == null) bestElevator = elevators.get(0);

        return bestElevator;
    }

    private boolean canServe(Elevator elevator, int reqFloor, ElevatorDirection reqDirection){
        if(elevator.getDirection() == ElevatorDirection.IDLE) return true;

        if(elevator.getDirection() == reqDirection){
            if(reqDirection == ElevatorDirection.UP && elevator.getCurrentFloor() < reqFloor) return true;
            if(reqDirection == ElevatorDirection.DOWN && elevator.getCurrentFloor() > reqFloor) return true;
        }

        return  false;
    }
}

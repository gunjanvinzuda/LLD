package org.example;

import java.util.List;

public interface ElevatorSelectionStrategy {
    Elevator selectElevator(List<Elevator> elevators, int reqFloor, ElevatorDirection reqDirection);
}

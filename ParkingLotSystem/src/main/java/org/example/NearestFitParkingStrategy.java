package org.example;

import java.util.List;
import java.util.Optional;

public class NearestFitParkingStrategy implements ParkingStrategy{

    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Size vehicleSize) {
        for (ParkingFloor floor: floors){
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicleSize);
            if(spot.isPresent()) return spot;
        }
        return Optional.empty();
    }
}

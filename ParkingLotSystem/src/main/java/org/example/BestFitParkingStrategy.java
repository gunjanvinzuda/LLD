package org.example;

import java.util.List;
import java.util.Optional;

public class BestFitParkingStrategy implements ParkingStrategy{

    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, Size vehicleSize) {
        Optional<ParkingSpot> bestSpot = Optional.empty();

        for(ParkingFloor floor: floors){
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicleSize);

            if(spot.isPresent()){
                if(bestSpot.isEmpty() || bestSpot.get().getSpotSize().ordinal() > spot.get().getSpotSize().ordinal()){
                    bestSpot = spot;
                }
            }
        }
        return bestSpot;
    }
}

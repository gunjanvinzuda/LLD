package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ParkingFloor {
    private final List<ParkingSpot> spots;
    private final int id;

    public ParkingFloor(int floorNum){
        this.id = floorNum;
        this.spots = new ArrayList<>();
    }

    public void addParkingSpot(ParkingSpot spot){
        this.spots.add(spot);
    }

    public Optional<ParkingSpot> findAvailableSpot(Size vehicleSize){
        return spots.stream()
                .filter(spot -> spot.canPark(vehicleSize))
                .min(Comparator.comparing(ParkingSpot::getSpotSize));
    }
}

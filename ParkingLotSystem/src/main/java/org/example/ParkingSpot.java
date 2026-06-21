package org.example;

public class ParkingSpot {
    private final String id;
    private final Size spotSize;
    private boolean available;

    public ParkingSpot(String id, Size size){
        this.id = id;
        this.spotSize = size;
        this.available = true;
    }

    public synchronized void parkVehicle(){
        if(!this.available) throw new IllegalStateException("ParkingSpot: parkVehicle: Spot not available: "+id);

        this.available = false;
    }

    public void unparkVehicle(){
        this.available = true;
    }

    public Size getSpotSize() {
        return spotSize;
    }

    public boolean canPark(Size vehicleSize){
        if(!this.available) return false;

        return vehicleSize.fitsIn(spotSize);
    }
}

package org.example;

import java.util.UUID;

public class ParkingTicket {
    private final String id;
    private final String vehicleNumber;
    private final Size vehicleSize;
    private final ParkingSpot spot;
    private final long entryTime;
    private long exitTime;

    public ParkingTicket(Size vehicleSize, ParkingSpot spot, String vehicleNumber){
        this.id = UUID.randomUUID().toString();
        this.entryTime = System.currentTimeMillis();
        this.vehicleNumber = vehicleNumber;
        this.vehicleSize = vehicleSize;
        this.spot = spot;
    }

    public String getId() {
        return id;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    public long getEntryTime(){
        return this.entryTime;
    }

    public long getExitTime() {
        if (exitTime == 0L) {
            throw new IllegalStateException("ParkingTicket: Exit time not set.");
        }
        return exitTime;
    }

    public Size getVehicleSize() {
        return vehicleSize;
    }


    public ParkingSpot getSpot() {
        return spot;
    }
}

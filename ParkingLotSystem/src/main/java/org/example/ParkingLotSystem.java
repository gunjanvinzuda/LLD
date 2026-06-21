package org.example;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLotSystem {
    private static final ParkingLotSystem INSTANCE = new ParkingLotSystem();
    private final List<ParkingFloor> floors;
    private final Map<String, ParkingTicket> activeTickets;
    private ParkingStrategy parkingStrategy;
    private FareStrategy fareStrategy;

    private ParkingLotSystem(){
        this.floors = new ArrayList<>();
        this.activeTickets = new ConcurrentHashMap<>();
    }

    public void init(ParkingStrategy parkingStrategy, FareStrategy fareStrategy){
        this.parkingStrategy = parkingStrategy;
        this.fareStrategy = fareStrategy;
    }

    public static ParkingLotSystem getInstance(){
        return INSTANCE;
    }

    public void addFloor(ParkingFloor floor){
        this.floors.add(floor);
    }

    private ParkingTicket generateParkingTicket(Size vehicleSize, String vehicleNumber, ParkingSpot spot){
        ParkingTicket ticket = new ParkingTicket(vehicleSize, spot, vehicleNumber);
        activeTickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public Optional<ParkingTicket> parkVehicle(Size vehicleSize, String vehicleNumber){
        Optional<ParkingSpot> spotOptional = parkingStrategy.findSpot(floors, vehicleSize);
        if(spotOptional.isPresent()){
            ParkingSpot spot = spotOptional.get();

            spot.parkVehicle();
            ParkingTicket ticket = generateParkingTicket(vehicleSize, vehicleNumber, spot);

            return Optional.of(ticket);
        }
        System.out.println("ParkingLotSystem: parkVehicle: No space available");
        return Optional.empty();
    }

    public Double exitVehicle(String ticketNumber){
        ParkingTicket ticket = activeTickets.get(ticketNumber);
        if(ticket == null){
            throw new IllegalArgumentException("ParkingLotSystem: exitVehicle: Invalid ticket.");
        }

        ticket.setExitTime(System.currentTimeMillis());
        Double fare = fareStrategy.calculateFare(ticket);

        ticket.getSpot().unparkVehicle();
        activeTickets.remove(ticketNumber);


        return fare;
    }
}

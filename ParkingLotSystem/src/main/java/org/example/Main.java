package org.example;

import java.util.Optional;

public class Main {
    static void main() {
        ParkingFloor floor1 = new ParkingFloor(1);
        ParkingFloor floor2 = new ParkingFloor(2);

        floor1.addParkingSpot(new ParkingSpot("F1-S1", Size.SMALL));
        floor1.addParkingSpot(new ParkingSpot("F1-S2", Size.SMALL));
        floor1.addParkingSpot(new ParkingSpot("F1-M1", Size.MEDIUM));
        floor1.addParkingSpot(new ParkingSpot("F1-M2", Size.MEDIUM));
        floor1.addParkingSpot(new ParkingSpot("F1-L1", Size.LARGE));

        floor2.addParkingSpot(new ParkingSpot("F2-S1", Size.SMALL));
        floor2.addParkingSpot(new ParkingSpot("F2-M1", Size.MEDIUM));
        floor2.addParkingSpot(new ParkingSpot("F2-L1", Size.LARGE));

        ParkingLotSystem lotSystem = ParkingLotSystem.getInstance();
        lotSystem.init(new BestFitParkingStrategy(), new VehicleBasedFareStrategy());

        lotSystem.addFloor(floor1);
        lotSystem.addFloor(floor2);

        Optional<ParkingTicket> ticket1 = lotSystem.parkVehicle(Size.LARGE, "GJ1234");
        Optional<ParkingTicket> ticket2 = lotSystem.parkVehicle(Size.LARGE, "GJ1234");

        Optional<ParkingTicket> ticket3 =  lotSystem.parkVehicle(Size.LARGE, "GJ1234");

        System.out.println("Fare for vehicle 2:" + lotSystem.exitVehicle(ticket2.get().getId()));

        ticket3 =  lotSystem.parkVehicle(Size.LARGE, "GJ1234");

        try{
            Thread.sleep(1000);
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }

        System.out.println("Fare for vehicle 1:" + lotSystem.exitVehicle(ticket1.get().getId()));
        System.out.println("Fare for vehicle 3:" + lotSystem.exitVehicle(ticket3.get().getId()));
    }
}

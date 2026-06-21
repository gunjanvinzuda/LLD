package org.example;

public class VehicleBasedFareStrategy implements FareStrategy{
    private static final long MILLIS_PER_HOUR = 1000*60*60;

    @Override
    public Double calculateFare(ParkingTicket ticket) {
        long duration = ticket.getExitTime() - ticket.getEntryTime();
        long hours =  (long) Math.max(1, Math.ceil(duration / (double) MILLIS_PER_HOUR));
        return hours * ticket.getVehicleSize().getHourlyRate();
    }
}

package org.example;

public class FlatRateFareStrategy implements FareStrategy{
    private final double RATE_PER_HOUR;
    private static final long MILLIS_PER_HOUR = 1000*60*60;

    public FlatRateFareStrategy(int rate){
        this.RATE_PER_HOUR = rate;
    }

    @Override
    public Double calculateFare(ParkingTicket ticket) {
        long duration = ticket.getExitTime() - ticket.getEntryTime();
        long hours = (long) Math.max(1, Math.ceil(duration / (double) MILLIS_PER_HOUR));
        return hours*RATE_PER_HOUR;
    }
}

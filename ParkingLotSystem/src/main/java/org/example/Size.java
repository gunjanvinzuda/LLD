package org.example;

public enum Size {
    SMALL(20.0){
        @Override
        public boolean fitsIn(Size spotSize){
            return spotSize == SMALL;
        }
    },
    MEDIUM(50.0){
        @Override
        public boolean fitsIn(Size spotSize) {
            return spotSize == MEDIUM || spotSize == LARGE;
        }
    },
    LARGE(100.0){
        @Override
        public boolean fitsIn(Size spotSize) {
            return spotSize == LARGE;
        }
    };

    private final double hourlyRate;

    Size(double rate){
        this.hourlyRate = rate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public abstract boolean fitsIn(Size vehicleSize);
}

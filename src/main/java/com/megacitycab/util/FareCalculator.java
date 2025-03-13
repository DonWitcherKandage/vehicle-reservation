package com.megacitycab.util;

public class FareCalculator {

    private static final double BASE_FARE = 5.0;  // Base fare in currency
    private static final double PER_KM_RATE_CAR = 10.0;
    private static final double PER_KM_RATE_VAN = 12.0;
    private static final double PER_KM_RATE_SUV = 15.0;

    public static double calculateFare(String vehicleType, double distanceKm) {
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero.");
        }

        double ratePerKm = switch (vehicleType.toUpperCase()) {
            case "CAR" -> PER_KM_RATE_CAR;
            case "VAN" -> PER_KM_RATE_VAN;
            case "SUV" -> PER_KM_RATE_SUV;
            default -> throw new IllegalArgumentException("Invalid vehicle type selected!");
        };

        return BASE_FARE + (ratePerKm * distanceKm);
    }
}
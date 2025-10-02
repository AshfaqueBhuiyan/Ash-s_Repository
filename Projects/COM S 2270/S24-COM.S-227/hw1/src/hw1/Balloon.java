package hw1;

/**
 * A Model for The Flight of a Hot Air Balloon, 
 * Considering Factors like Air Temperature, Fuel Availability, and Balloon Mass.
 * 
 * @author Ashfaque Bhuiyan (mbhuiyan)
 */

public class Balloon {
	
    // Provided Simulation Constants
	
    private static final double HEAT_LOSS = 0.1; // Heat Loss Factor.
    private static final double VOLUME = 61234; // in m^3
    private static final double GRAVITY = 9.81; // in m/s^2
    private static final double GAS_CONSTANT = 287.05; // in J/kgK
    private static final double STANDARD_PRESSURE = 1013.25; // in hPa
    private static final double KELVIN_OFFSET = 273.15; // Kelvins at 0 degrees C.

    // Instance Variables
    
    private double initialOutsideAirTemp; // Initial Outside Air Temperature.
    private double outsideAirTemp; // Outside Air Temperature in C.
    private double initialWindDirection; // Initial Wind Direction.
    private double windDirection; // Wind Direction in Degrees.
    private double balloonTemp; // Balloon Air Temperature in C.
    private double fuelRemaining; // Fuel Remaining in kBTU.
    private double fuelBurnRate; // Fuel Burn Rate in kBTU/s.
    private double balloonMass; // Balloon Mass in kg.
    private double tetherLength; // Tether Length in meters.
    private double altitude; // Balloon Altitude in meters.
    private double velocity; // Balloon Velocity in m/s.
    private long simulationTime; // Simulation Time in seconds.

    /**
     * Constructs a new Balloon Simulation.
     * 
     * @param airTemp (the Outside Air Temperature in C).
     * @param windDirection (the Wind Direction in Degrees).
     */
    
    public Balloon(double airTemp, double windDirection) {
    	this.initialOutsideAirTemp = airTemp;
    	this.outsideAirTemp = airTemp;
    	this.initialWindDirection = windDirection;
        this.windDirection = windDirection % 360;
        this.balloonTemp = airTemp;
        this.fuelRemaining = 0;
        this.fuelBurnRate = 0;
        this.balloonMass = 0;
        this.tetherLength = 0;
        this.altitude = 0;
        this.velocity = 0;
        this.simulationTime = 0;
    }

    // Getters and Setters
    
    public double getFuelRemaining() {
        return fuelRemaining;
    }

    public void setFuelRemaning(double fuel) {
        this.fuelRemaining = Math.max(0, fuel);
    }

    public double getBalloonMass() {
        return balloonMass;
    }

    public void setBalloonMass(double mass) {
        this.balloonMass = Math.max(0, mass);
    }

    public double getOutsideAirTemp() {
        return outsideAirTemp;
    }

    public void setOutsideAirTemp(double temp) {
        this.outsideAirTemp = temp;
    }

    public double getFuelBurnRate() {
        return fuelBurnRate;
    }

    public void setFuelBurnRate(double rate) {
        this.fuelBurnRate = Math.max(0, rate);
    }

    public double getBalloonTemp() {
        return balloonTemp;
    }

    public void setBalloonTemp(double temp) {
        this.balloonTemp = temp;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getAltitude() {
        return altitude;
    }

    public double getTetherLength() {
        return tetherLength;
    }

    public void setTetherLength(double length) {
        this.tetherLength = Math.max(0, length);
    }

    public double getTetherRemaining() {
        return Math.max(0, tetherLength - altitude);
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void changeWindDirection(double deg) {
        this.windDirection = (this.windDirection + deg) % 360;
        if (this.windDirection < 0) {
            this.windDirection += 360;
        }
    }

    public long getMinutes() {
        return simulationTime / 60;
    }

    public long getSeconds() {
        return simulationTime % 60;
    }

    // Simulation Calculations for Balloon Flight
    
    public void update() {
        double fuelUsed = Math.min(fuelRemaining, fuelBurnRate);
        fuelRemaining -= fuelUsed;

        double deltaT = fuelUsed + (outsideAirTemp - balloonTemp) * HEAT_LOSS;
        balloonTemp += deltaT;

        double densityOutside = STANDARD_PRESSURE / (GAS_CONSTANT * (outsideAirTemp + KELVIN_OFFSET));
        double densityInside = STANDARD_PRESSURE / (GAS_CONSTANT * (balloonTemp + KELVIN_OFFSET));

        double forceLift = VOLUME * (densityOutside - densityInside) * GRAVITY;
        double forceGravity = balloonMass * GRAVITY;
        double netForce = forceLift - forceGravity;

        double netAcceleration = netForce / balloonMass;
        velocity += netAcceleration;
        altitude += velocity;

        // Enforcing Altitude Bounds
        
        altitude = Math.max(0, altitude);
        altitude = Math.min(altitude, tetherLength);

        simulationTime += 1;
    }

    // Method to Reset the Balloon to its Initial State
    
    public void reset() {
    	this.outsideAirTemp = initialOutsideAirTemp;
        this.windDirection = initialWindDirection;
        this.balloonTemp = initialOutsideAirTemp;
        this.fuelRemaining = 0;
        this.fuelBurnRate = 0;
        this.balloonMass = 0;
        this.tetherLength = 0;
        this.altitude = 0;
        this.velocity = 0;
        this.simulationTime = 0;
    }
}

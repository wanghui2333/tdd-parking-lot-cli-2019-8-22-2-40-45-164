package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        // TODO: Please implement the method
    	ParkingTicket parkingTicket = parkingLot.park(car);
    	if (parkingTicket == null) {
    		lastErrorMessage = "The parking lot is full.";
		}else {
			lastErrorMessage = null;
		}
    	
        return parkingTicket;
    }

 // 
    public Car fetch(ParkingTicket ticket) {
        // TODO: Please implement the method
    	if (ticket == null) {
			lastErrorMessage = "Please provide your parking ticket.";
			return null;
		}
    	
    	Car car = parkingLot.fetch(ticket);
    	if (car == null) {
			lastErrorMessage = "Unrecognized parking ticket.";
			return null;
		}
        return car;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

	public ParkingLot getParkingLot() {
		return parkingLot;
	}
}

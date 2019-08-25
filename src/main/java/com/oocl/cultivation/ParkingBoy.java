package com.oocl.cultivation;

public class ParkingBoy {

    private final ParkingLot parkingLot;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingTicket park(Car car) {
        // TODO: Please implement the method
        return parkingLot.park(car);
    }

 // 
    public Car fetch(ParkingTicket ticket) {
        // TODO: Please implement the method
        return parkingLot.fetch(ticket);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

	public ParkingLot getParkingLot() {
		return parkingLot;
	}
}

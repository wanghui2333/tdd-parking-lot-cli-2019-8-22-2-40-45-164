package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableParkingPosition() {
        return getCapacity() - cars.size();
    }
    
    public ParkingTicket park(Car car) {
    	if(getAvailableParkingPosition() == 0) {
    		return null;
    	}
    	ParkingTicket parkingTicket = new ParkingTicket();
    	cars.put(parkingTicket, car);
		return parkingTicket;
	}
    
    public Car fetch(ParkingTicket ticket) {
        // TODO: Please implement the method
    	Car car = cars.remove(ticket);
    	
    	return car;
    }

	public int getCapacity() {
		return capacity;
	}
}

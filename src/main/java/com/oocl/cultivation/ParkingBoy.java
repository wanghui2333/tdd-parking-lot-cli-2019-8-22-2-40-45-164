package com.oocl.cultivation;

import java.util.ArrayList;

public class ParkingBoy {

    private final ArrayList<ParkingLot> parkingList;
    private String lastErrorMessage;

    public ParkingBoy(ParkingLot parkingLot) {
    	ArrayList<ParkingLot> parkingList = new ArrayList<ParkingLot>();
    	parkingList.add(parkingLot);
    	this.parkingList = parkingList;
    }

    public ParkingBoy(ArrayList<ParkingLot> parkingList) {
		// TODO Auto-generated constructor stub
    	this.parkingList = parkingList;
	}

	public ParkingTicket park(Car car) {
        // TODO: Please implement the method
		ParkingTicket parkingTicket = null;
		
		for(ParkingLot parkingLot : parkingList) {
			parkingTicket = parkingLot.park(car);
			if(parkingTicket != null) {
				break;
			}
		}
		
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
    	
    	Car car = null;
    	for(ParkingLot parkingLot : parkingList) {
			car = parkingLot.fetch(ticket);
			if(car != null) {
				break;
			}
		}
    	
    	if (car == null) {
			lastErrorMessage = "Unrecognized parking ticket.";
			return null;
		}
        return car;
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

	public ArrayList<ParkingLot> getParkingList() {
		return parkingList;
	}
}

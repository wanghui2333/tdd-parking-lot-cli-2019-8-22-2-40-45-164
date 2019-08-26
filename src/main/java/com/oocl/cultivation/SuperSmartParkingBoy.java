package com.oocl.cultivation;

import java.util.PriorityQueue;

public class SuperSmartParkingBoy {

	private PriorityQueue<ParkingLot> parkingQueue = null;
	private String lastErrorMessage;

	public SuperSmartParkingBoy() {
		this.parkingQueue = new PriorityQueue<ParkingLot>((x, y) -> {
			double one = (double) x.getAvailableParkingPosition() / x.getCapacity();
			double two = (double) y.getAvailableParkingPosition() / y.getCapacity();
			return Double.compare(one, two);
		});
	}

	public void addParkingLot(ParkingLot parkingLot) {
		getParkingQueue().add(parkingLot);
	}

	public ParkingTicket park(Car car) {
		// TODO: Please implement the method
		ParkingLot parkingLot = getParkingQueue().poll();
		ParkingTicket parkingTicket = parkingLot.park(car);
		addParkingLot(parkingLot);

		if (parkingTicket == null) {
			lastErrorMessage = "The parking lot is full.";
		} else {
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
		for (ParkingLot parkingLot : getParkingQueue()) {
			car = parkingLot.fetch(ticket);
			if (car != null) {
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

	public PriorityQueue<ParkingLot> getParkingQueue() {
		return parkingQueue;
	}

	public void setParkingQueue(PriorityQueue<ParkingLot> parkingQueue) {
		this.parkingQueue = parkingQueue;
	}

}

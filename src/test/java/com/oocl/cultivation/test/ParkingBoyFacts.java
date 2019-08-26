package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.ParkingBoy;
import com.oocl.cultivation.ParkingLot;
import com.oocl.cultivation.ParkingTicket;
import com.oocl.cultivation.SmartParkingBoy;
import com.oocl.cultivation.SuperSmartParkingBoy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ParkingBoyFacts {
	@Test
	void should_park_a_car_to_a_parking_lot_and_get_it_back() {
		ParkingLot parkingLot = new ParkingLot();
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
		Car car = new Car();

		ParkingTicket ticket = parkingBoy.park(car);
		Car fetched = parkingBoy.fetch(ticket);

		assertSame(fetched, car);
	}

	@Test
	void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
		ParkingLot parkingLot = new ParkingLot();
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
		Car firstCar = new Car();
		Car secondCar = new Car();

		ParkingTicket firstTicket = parkingBoy.park(firstCar);
		ParkingTicket secondTicket = parkingBoy.park(secondCar);

		Car fetchedByFirstTicket = parkingBoy.fetch(firstTicket);
		Car fetchedBySecondTicket = parkingBoy.fetch(secondTicket);

		assertSame(firstCar, fetchedByFirstTicket);
		assertSame(secondCar, fetchedBySecondTicket);
	}

	@Test
	void should_not_fetch_any_car_once_ticket_is_wrong() {
		ParkingLot parkingLot = new ParkingLot();
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
		Car car = new Car();
		ParkingTicket wrongTicket = new ParkingTicket();

		ParkingTicket ticket = parkingBoy.park(car);

		assertNull(parkingBoy.fetch(wrongTicket));
		assertSame(car, parkingBoy.fetch(ticket));
	}

	@Test
	void should_query_message_once_the_ticket_is_wrong() {
		ParkingLot parkingLot = new ParkingLot();
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
		ParkingTicket wrongTicket = new ParkingTicket();

		parkingBoy.fetch(wrongTicket);
		String message = parkingBoy.getLastErrorMessage();

		assertEquals("Unrecognized parking ticket.", message);
	}

	@Test
	void should_clear_the_message_once_the_operation_is_succeeded() {
		ParkingLot parkingLot = new ParkingLot();
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
		ParkingTicket wrongTicket = new ParkingTicket();

		parkingBoy.fetch(wrongTicket);
		assertNotNull(parkingBoy.getLastErrorMessage());

		ParkingTicket ticket = parkingBoy.park(new Car());
		assertNotNull(ticket);
		assertNull(parkingBoy.getLastErrorMessage());
	}

	@Test
	void should_not_fetch_any_car_once_ticket_is_not_provided() {
		ParkingLot parkingLot = new ParkingLot();
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
		Car car = new Car();

		ParkingTicket ticket = parkingBoy.park(car);

		assertNull(parkingBoy.fetch(null));
		assertSame(car, parkingBoy.fetch(ticket));
	}

	@Test
	void should_query_message_once_ticket_is_not_provided() {
		ParkingLot parkingLot = new ParkingLot();
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

		parkingBoy.fetch(null);

		assertEquals("Please provide your parking ticket.", parkingBoy.getLastErrorMessage());
	}

	@Test
	void should_not_fetch_any_car_once_ticket_has_been_used() {
		ParkingLot parkingLot = new ParkingLot();
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
		Car car = new Car();

		ParkingTicket ticket = parkingBoy.park(car);
		parkingBoy.fetch(ticket);

		assertNull(parkingBoy.fetch(ticket));
	}

	@Test
	void should_query_error_message_for_used_ticket() {
		ParkingLot parkingLot = new ParkingLot();
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
		Car car = new Car();

		ParkingTicket ticket = parkingBoy.park(car);
		parkingBoy.fetch(ticket);
		parkingBoy.fetch(ticket);

		assertEquals("Unrecognized parking ticket.", parkingBoy.getLastErrorMessage());
	}

	@Test
	void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
		final int capacity = 1;
		ParkingLot parkingLot = new ParkingLot(capacity);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

		parkingBoy.park(new Car());

		assertNull(parkingBoy.park(new Car()));
	}

	@Test
	void should_get_message_if_there_is_not_enough_position() {
		final int capacity = 1;
		ParkingLot parkingLot = new ParkingLot(capacity);
		ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

		parkingBoy.park(new Car());
		parkingBoy.park(new Car());

		assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
	}

	@Test
	void should_park_mutl_car_to_a_parking_lot_and_get_it_back() {
		final int capacity = 1;
		ParkingLot parkingLot = new ParkingLot(capacity);
		ParkingLot parkingLot2 = new ParkingLot(capacity);
		Car car = new Car();
		Car car2 = new Car();

		ArrayList<ParkingLot> parkingList = new ArrayList<ParkingLot>();

		parkingList.add(parkingLot);
		parkingList.add(parkingLot2);
		ParkingBoy parkingBoy = new ParkingBoy(parkingList);

		ParkingTicket parkingTicket = parkingBoy.park(car);
		ParkingTicket parkingTicket2 = parkingBoy.park(car2);

		Car fetch = parkingLot2.fetch(parkingTicket2);

		assertSame(fetch, car2);

	}

	
	@Test
	void should_park_mutl_car_to_smartBoy_parking_lot_and_get_it_back() {
		final int capacity = 2;
		ParkingLot parkingLot = new ParkingLot(capacity);
		ParkingLot parkingLot2 = new ParkingLot(capacity);
		Car car = new Car();
		Car car2 = new Car();
		Car car3 = new Car();
		Car car4 = new Car();
		
		SmartParkingBoy smartParkingBoy = new SmartParkingBoy();

		smartParkingBoy.addParkingLot(parkingLot);
		smartParkingBoy.addParkingLot(parkingLot2);
		ParkingTicket parkingTicket = smartParkingBoy.park(car);
		ParkingTicket parkingTicket2 = smartParkingBoy.park(car2);
		ParkingTicket parkingTicket3 = smartParkingBoy.park(car3);
		ParkingTicket parkingTicket4 = smartParkingBoy.park(car4);
		
		Car fetch = parkingLot.fetch(parkingTicket);
		Car fetch2 = parkingLot2.fetch(parkingTicket2);
		Car fetch3 = parkingLot.fetch(parkingTicket3);
		Car fetch4 = parkingLot2.fetch(parkingTicket4);
		
		
		assertSame(car, fetch);
		assertSame(car2, fetch2);
		assertSame(car3, fetch3);
		assertSame(car4, fetch4);

		
		Map<String, String> map = new HashMap<String, String>();
	}
	
	@Test
	void should_park_mutl_car_to_superSmartBoy_parking_lot_and_get_it_back() {
		final int capacity = 2;
		ParkingLot parkingLot = new ParkingLot(100);
		ParkingLot parkingLot2 = new ParkingLot(capacity);
		Car car = new Car();
		Car car2 = new Car();
		Car car3 = new Car();
		Car car4 = new Car();
		
		SuperSmartParkingBoy superSmartParkingBoy= new SuperSmartParkingBoy();

		superSmartParkingBoy.addParkingLot(parkingLot);
		superSmartParkingBoy.addParkingLot(parkingLot2);
		ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
		ParkingTicket parkingTicket2 = superSmartParkingBoy.park(car2);
		ParkingTicket parkingTicket3 = superSmartParkingBoy.park(car3);
		ParkingTicket parkingTicket4 = superSmartParkingBoy.park(car4);
		
		Car fetch = parkingLot.fetch(parkingTicket);
		Car fetch2 = parkingLot.fetch(parkingTicket2);
		Car fetch3 = parkingLot.fetch(parkingTicket3);
		Car fetch4 = parkingLot.fetch(parkingTicket4);
		
		assertSame(car, fetch);
		assertSame(car2, fetch2);
		assertSame(car3, fetch3);
		assertSame(car4, fetch4);

	}
}

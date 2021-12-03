package com.parkinglot;

import com.parkinglot.exception.*;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
  @Test
  public void should_return_ticket_when_parking_given_a_car() {
    ParkingLot parkingLot = new ParkingLot();
    Car car = new Car();

    Ticket ticket = parkingLot.park(car);
    assertNotNull(ticket);
  }

  @Test
  public void should_return_a_car_when_fetching_given_a_ticket() {
    ParkingLot parkingLot = new ParkingLot();
    Ticket ticket = new Ticket();

    Car car = parkingLot.fetch(ticket);
    assertNotNull(car);
  }

//  @Test
//  public void should_return_null_when_parking_lot_is_full_given_a_car() {
//    ParkingLot parkingLot = new ParkingLot(1);
//    parkingLot.park(new Car());
//    Car car = new Car();
//
//    Ticket ticket = parkingLot.park(car);
//    assertNull(ticket);
//  }

//  @Test
//  public void should_return_null_when_fetching_given_a_wrong_ticket() {
//    //  given no ticket
//    ParkingLot parkingLot = new ParkingLot();
//    Car carFromNoTicket = parkingLot.fetch(null);
//    assertNull(carFromNoTicket);
//
//    // given wrong ticket
//    Car carFromWrongTicket = parkingLot.fetch(new Ticket());
//    assertNull(carFromWrongTicket);
//  }
//
//  @Test
//  public void should_return_null_when_fetching_given_a_used_ticket() {
//    ParkingLot parkingLot = new ParkingLot();
//    Ticket ticket = parkingLot.park(new Car());
//    parkingLot.fetch(ticket);
//
//    Car car = parkingLot.fetch(ticket);
//    assertNull(car);
//  }

  @Test
  public void should_return_2_correct_cars_when_fetching_given_2_tickets() {
    ParkingLot parkingLot = new ParkingLot();
    Car firstParkedCar = new Car();
    Ticket firstTicket = parkingLot.park(firstParkedCar);
    Car secondParkedCar = new Car();
    Ticket secondTicket = parkingLot.park(secondParkedCar);

    Car carFetchFromFirstTicket = parkingLot.fetch(firstTicket);
    Car carFetchFromSecondTicket = parkingLot.fetch(secondTicket);

    assertThat(carFetchFromFirstTicket).isEqualTo(firstParkedCar);
    assertThat(carFetchFromSecondTicket).isEqualTo(secondParkedCar);
  }

  @Test
  public void should_throw_no_available_position_exception_when_parking_given_parking_lot_no_capacity() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());
    Car car = new Car();

    NoAvailablePositionException noAvailablePositionException = assertThrows(NoAvailablePositionException.class, () -> {
      parkingLot.park(car);
    });
    assertEquals("No available position.", noAvailablePositionException.getMessage());
  }

  @Test
  public void should_throw_unrecognized_parking_ticket_exception_when_fetching_given_ticket_is_unrecognized() {
    ParkingLot parkingLot = new ParkingLot();
    UnrecognizedTicketException unrecognizedWrongTicket = assertThrows(UnrecognizedTicketException.class, () -> {
      parkingLot.fetch(new Ticket());
    });
    assertEquals("Unrecognized parking ticket.", unrecognizedWrongTicket.getMessage());

    Ticket ticket = parkingLot.park(new Car());
    parkingLot.fetch(ticket);
    UnrecognizedTicketException unrecognizedUsedTicket = assertThrows(UnrecognizedTicketException.class, () -> {
      parkingLot.fetch(ticket);
    });
    assertEquals("Unrecognized parking ticket.", unrecognizedUsedTicket.getMessage());
  }
}

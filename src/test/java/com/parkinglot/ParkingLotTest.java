package com.parkinglot;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParkingLotTest {
  @Test
  public void should_return_ticket_when_parking_given_a_car() {
    ParkingLot parkingLot = new ParkingLot(10);
    Car car = new Car();

    Ticket ticket = parkingLot.park(car);
    assertNotNull(ticket);
  }

  @Test
  public void should_return_a_car_when_fetching_given_a_ticket() {
    ParkingLot parkingLot = new ParkingLot(10);
    Ticket ticket = new Ticket();

    Car car = parkingLot.fetch(ticket);
    assertNotNull(car);
  }

  @Test
  public void should_return_null_when_parking_lot_is_full_given_a_car() {
    ParkingLot parkingLot = new ParkingLot(1);
    parkingLot.park(new Car());

    Car car = new Car();

    Ticket ticket = parkingLot.park(car);
    assertNull(ticket);
  }

  @Test
  public void should_return_null_when_fetching_given_a_wrong_ticket() {
    //  given no ticket
    ParkingLot parkingLot = new ParkingLot(10);
    Car carFromNoTicket = parkingLot.fetch(null);
    assertNull(carFromNoTicket);

    // given wrong ticket
    Car carFromWrongTicket = parkingLot.fetch(new Ticket());
    assertNull(carFromWrongTicket);
  }

  @Test
  public void should_return_null_when_fetching_given_a_used_ticket() {
    ParkingLot parkingLot = new ParkingLot(10);
    Ticket ticket = parkingLot.park(new Car());
    parkingLot.fetch(ticket);

    Car car = parkingLot.fetch(ticket);
    assertNull(car);
  }
}

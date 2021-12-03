package com.parkinglot;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}

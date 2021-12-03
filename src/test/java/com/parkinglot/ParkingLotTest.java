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
}

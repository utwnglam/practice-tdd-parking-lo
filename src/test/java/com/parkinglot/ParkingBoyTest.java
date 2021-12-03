package com.parkinglot;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
  @Test
  public void should_return_ticket_when_parking_given_parking_boy_manage_one_parking_lot() {
      ParkingLot parkingLot = new ParkingLot();
      ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

      Ticket ticket = parkingBoy.park(new Car());

      assertNotNull(ticket);
  }
}

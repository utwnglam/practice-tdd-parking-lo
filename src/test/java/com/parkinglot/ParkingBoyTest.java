package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ParkingBoyTest {
  @Test
  public void should_return_ticket_when_parking_given_parking_boy_manage_one_parking_lot() {
      ParkingLot parkingLot = new ParkingLot();
      ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));

      Ticket ticket = parkingBoy.park(new Car());

      assertNotNull(ticket);
  }

  @Test
  public void should_park_to_first_lot_when_parking_given_parking_boy_manage_two_parking_lots_and_both_available() {
    ParkingLot firstParkingLot = new ParkingLot();
    ParkingLot secondParkingLot = new ParkingLot();
    List<ParkingLot> parkingLotList = Arrays.asList(firstParkingLot, secondParkingLot);

    ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
    Ticket ticket = parkingBoy.park(new Car());

    assertNotNull(ticket);
    assertEquals(9, firstParkingLot.getAvailablePosition());
    assertEquals(10, secondParkingLot.getAvailablePosition());
  }
}

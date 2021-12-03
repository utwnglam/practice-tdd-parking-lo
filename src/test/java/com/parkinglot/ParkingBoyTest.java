package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;
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

  @Test
  public void should_return_right_when_parking_given_parking_boy_manage_two_parking_lots_and_both_have_1car() {
    ParkingLot firstParkingLot = new ParkingLot(1);
    ParkingLot secondParkingLot = new ParkingLot(1);
    List<ParkingLot> parkingLotList = Arrays.asList(firstParkingLot, secondParkingLot);

    ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
    Car firstParkedCar = new Car();
    Ticket firstTicket = parkingBoy.park(firstParkedCar);
    Car secondParkedCar = new Car();
    Ticket secondTicket = parkingBoy.park(secondParkedCar);

    Car carFetchFromFirstTicket = parkingBoy.fetch(firstTicket);
    Car carFetchFromSecondTicket = parkingBoy.fetch(secondTicket);

    assertThat(carFetchFromFirstTicket).isEqualTo(firstParkedCar);
    assertThat(carFetchFromSecondTicket).isEqualTo(secondParkedCar);
  }

  @Test
  public void should_return_nothing_when_fetching_given_parking_boy_manage_two_parking_lots_and_unrecognized_ticket() {
    ParkingLot firstParkingLot = new ParkingLot();
    ParkingLot secondParkingLot = new ParkingLot();
    List<ParkingLot> parkingLotList = Arrays.asList(firstParkingLot, secondParkingLot);
    ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

    //  wrong ticket
    UnrecognizedTicketException unrecognizedWrongTicket = assertThrows(UnrecognizedTicketException.class, () -> {
      parkingBoy.fetch(new Ticket());
    });
    assertEquals("Unrecognized parking ticket.", unrecognizedWrongTicket.getMessage());
  }

  @Test
  public void should_return_nothing_when_fetching_given_parking_boy_manage_two_parking_lots_and_used_ticket() {
    ParkingLot firstParkingLot = new ParkingLot();
    ParkingLot secondParkingLot = new ParkingLot();
    List<ParkingLot> parkingLotList = Arrays.asList(firstParkingLot, secondParkingLot);
    ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

    // used ticket
    Ticket ticket = parkingBoy.park(new Car());
    parkingBoy.fetch(ticket);
    UnrecognizedTicketException unrecognizedUsedTicket = assertThrows(UnrecognizedTicketException.class, () -> {
      parkingBoy.fetch(ticket);
    });
    assertEquals("Unrecognized parking ticket.", unrecognizedUsedTicket.getMessage());
  }
}

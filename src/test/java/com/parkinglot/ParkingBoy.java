package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;

public class ParkingBoy {
  private List<ParkingLot> parkingLots;

  public ParkingBoy(List<ParkingLot> parkingLot) {
    this.parkingLots = parkingLot;
  }

  public Ticket park(Car car) {
    return parkingLots.stream()
      .filter(parkingLot -> parkingLot.getAvailablePosition() > 0)
      .findFirst()
      .get()
      .park(car);
  }

  public Car fetch(Ticket ticket) {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.contains(ticket)) {
        return parkingLot.fetch(ticket);
      }
    }
    throw new UnrecognizedTicketException("Unrecognized parking ticket.");
  }
}

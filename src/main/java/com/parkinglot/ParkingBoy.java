package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;

public class ParkingBoy {
  private final List<ParkingLot> parkingLots;

  public ParkingBoy(List<ParkingLot> parkingLot) {
    this.parkingLots = parkingLot;
  }

  public Ticket park(Car car) {
    try {
      return parkingLots.stream()
        .filter(parkingLot -> parkingLot.getAvailablePosition() > 0)
        .findFirst()
        .get()
        .park(car);
    } catch (Exception exception) {
      throw new NoAvailablePositionException("No available position.");
    }
  }

  public Car fetch(Ticket ticket) {
    for (ParkingLot parkingLot : parkingLots) {
      if (parkingLot.contains(ticket)) {
        return parkingLot.fetch(ticket);
      }
    }
    throw new UnrecognizedTicketException("Unrecognized parking ticket.");
  }

  public List<ParkingLot> getParkingLots() {
    return parkingLots;
  }
}

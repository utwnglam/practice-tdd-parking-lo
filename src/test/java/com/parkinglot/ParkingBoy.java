package com.parkinglot;

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
    return parkingLots.stream()
      .filter(parkingLot -> parkingLot.contains(ticket))
      .findFirst()
      .get()
      .fetch(ticket);
  }
}

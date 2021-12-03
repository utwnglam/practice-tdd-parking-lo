package com.parkinglot;

import java.util.List;

public class ParkingBoy {
  private List<ParkingLot> parkingLot;

  public ParkingBoy(List<ParkingLot> parkingLot) {
    this.parkingLot = parkingLot;
  }

  public Ticket park(Car car) {
    return parkingLot.park(car);
  }
}

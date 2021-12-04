package com.parkinglot;

import java.util.List;

public class ParkingLotManager extends ParkingBoy {
  private List<ParkingBoy> managementList;

  public ParkingLotManager(List<ParkingBoy> managementList, List<ParkingLot> parkingLot) {
    super(parkingLot);
    this.managementList = managementList;
  }

  public boolean contains(ParkingBoy parkingBoy) {
    return managementList.contains(parkingBoy);
  }

  public Ticket tellBoyToPark(Car car, ParkingBoy parkingBoy) {
    return null;
  }

  public Car tellBoyToFetch(Ticket ticket, ParkingBoy parkingBoy) {
    return null;
  }
}

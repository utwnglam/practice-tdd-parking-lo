package com.parkinglot;

import java.util.List;

public class ParkingLotManager{
  private List<ParkingBoy> managementList;

  public ParkingLotManager(List<ParkingBoy> managementList) {
    this.managementList = managementList;
  }


  public boolean contains(ParkingBoy parkingBoy) {
    return managementList.contains(parkingBoy);
  }
}

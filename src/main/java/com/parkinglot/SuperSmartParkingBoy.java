package com.parkinglot;

import java.util.List;

public class SuperSmartParkingBoy extends SmartParkingBoy {
  public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
    super(parkingLotList);
  }

  @Override
  public Ticket park(Car car) {
    ParkingLot parkingLotWithLargerRate = getParkingLots().get(0);
    for (ParkingLot parkingLot : getParkingLots()) {
      parkingLotWithLargerRate =
        getAvailableRate(parkingLot) > getAvailableRate(parkingLotWithLargerRate)
          ? parkingLot : parkingLotWithLargerRate;
    }
    return parkingLotWithLargerRate.park(car);
  }

  private double getAvailableRate(ParkingLot parkingLot) {
    return (double) parkingLot.getAvailablePosition() / parkingLot.getCapacity();
  }
}

package com.parkinglot;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{
  public SmartParkingBoy(List<ParkingLot> parkingLotList) {
    super(parkingLotList);
  }

  @Override
  public Ticket park(Car car) {
    ParkingLot parkingLotMorePosition = Collections.max(
      this.getParkingLots(), Comparator.comparing(ParkingLot::getAvailablePosition)
    );
    return parkingLotMorePosition.park(car);
  }
}

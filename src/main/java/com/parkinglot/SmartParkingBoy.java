package com.parkinglot;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
  public SmartParkingBoy(List<ParkingLot> parkingLotList) {
    super(parkingLotList);
  }

  @Override
  public Ticket park(Car car) {
    try {
      ParkingLot parkingLotMorePosition = Collections.max(
        this.getParkingLots(), Comparator.comparing(ParkingLot::getAvailablePosition)
      );
      return parkingLotMorePosition.park(car);
    } catch (Exception exception) {
      if (countObservers() == 0) {
        throw exception;
      } else {
        setChanged();
        notifyObservers("Manager: No available position.");
        return null;
      }
    }
  }
}

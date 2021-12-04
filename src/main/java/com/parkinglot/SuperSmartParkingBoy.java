package com.parkinglot;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SuperSmartParkingBoy extends SmartParkingBoy {
  public SuperSmartParkingBoy(List<ParkingLot> parkingLotList) {
    super(parkingLotList);
  }

  @Override
  public Ticket park(Car car) {
    try {
      ParkingLot parkingLotWithLargerRate = Collections.max(
        this.getParkingLots(), Comparator.comparing(this::getAvailableRate)
      );
      return parkingLotWithLargerRate.park(car);
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

  private double getAvailableRate(ParkingLot parkingLot) {
    return (double) parkingLot.getAvailablePosition() / parkingLot.getCapacity();
  }
}

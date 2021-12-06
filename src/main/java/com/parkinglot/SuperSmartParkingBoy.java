package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;

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
      return Collections
        .max(this.getParkingLots(), Comparator.comparing(this::getAvailableRate))
        .park(car);
    } catch (NoAvailablePositionException exception) {
      if (countObservers() == 0) {
        throw exception;
      } else {
        setChanged();
        notifyObservers(ParkingLotManager.NoAvailablePositionManagerMessage);
        return null;
      }
    }
  }

  private double getAvailableRate(ParkingLot parkingLot) {
    return (double) parkingLot.getAvailablePosition() / parkingLot.getCapacity();
  }
}

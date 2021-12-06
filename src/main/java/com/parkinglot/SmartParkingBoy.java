package com.parkinglot;

import com.parkinglot.exception.NoAvailablePositionException;

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
      return Collections
        .max(this.getParkingLots(), Comparator.comparing(ParkingLot::getAvailablePosition))
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
}

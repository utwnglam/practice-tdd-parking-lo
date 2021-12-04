package com.parkinglot;

import com.parkinglot.exception.BoyNotInManagementListException;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ParkingLotManager extends ParkingBoy implements Observer {
  private final List<ParkingBoy> managementList;

  public ParkingLotManager(List<ParkingBoy> managementList, List<ParkingLot> parkingLot) {
    super(parkingLot);
    this.managementList = managementList;
    for (ParkingBoy parkingBoy : managementList) {
      parkingBoy.addObserver(this);
    }
  }

  public boolean contains(ParkingBoy parkingBoy) {
    return managementList.contains(parkingBoy);
  }

  public Ticket tellBoyToPark(Car car, ParkingBoy parkingBoy) {
    if (contains(parkingBoy)) {
      return parkingBoy.park(car);
    }
    throw new BoyNotInManagementListException("Parking boy is not managed by this parking manager.");
  }

  public Car tellBoyToFetch(Ticket ticket, ParkingBoy parkingBoy) {
    if (contains(parkingBoy)) {
      return parkingBoy.fetch(ticket);
    }
    throw new BoyNotInManagementListException("Parking boy is not managed by this parking manager.");
  }

  @Override
  public void update(Observable observable, Object exceptionFromBoys) {
    try {
      throw (Throwable) exceptionFromBoys;
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }
}

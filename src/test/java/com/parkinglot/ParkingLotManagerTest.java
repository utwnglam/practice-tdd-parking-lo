package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotManagerTest {
  private ParkingBoy parkingBoy;
  private SmartParkingBoy smartParkingBoy;
  private SuperSmartParkingBoy superSmartParkingBoy;

  @BeforeEach
  public void setup() {
    ParkingLot firstParkingLot = new ParkingLot();
    ParkingLot secondParkingLot = new ParkingLot();
    List<ParkingLot> parkingBoyLotList = Arrays.asList(firstParkingLot, secondParkingLot);
    parkingBoy = new ParkingBoy(parkingBoyLotList);

    ParkingLot thirdParkingLot = new ParkingLot();
    ParkingLot fourthParkingLot = new ParkingLot();
    List<ParkingLot> smartParkingBoyLotList = Arrays.asList(thirdParkingLot, fourthParkingLot);
    smartParkingBoy = new SmartParkingBoy(smartParkingBoyLotList);

    ParkingLot fifthParkingLot = new ParkingLot();
    ParkingLot sixthParkingLot = new ParkingLot();
    List<ParkingLot> superSmartParkingBoyLotList = Arrays.asList(fifthParkingLot, sixthParkingLot);
    superSmartParkingBoy = new SuperSmartParkingBoy(superSmartParkingBoyLotList);
  }

  @Test
  public void should_return_true_when_add_parkingBoy_to_list_given_parkingLotManager_have_managementList() {
    List<ParkingBoy> managementList = Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy);
    List<ParkingLot> parkingLotList = Collections.emptyList();

    ParkingLotManager parkingLotManager = new ParkingLotManager(managementList, parkingLotList);
    assertTrue(parkingLotManager.contains(parkingBoy));
  }

  @Test
  public void should_park_to_first_lot_when_parking_given_parking_manager_manage_two_parking_lots_and_both_available() {
    List<ParkingBoy> managementList = Arrays.asList(parkingBoy, smartParkingBoy, superSmartParkingBoy);

    ParkingLot seventhParkingLot = new ParkingLot();
    ParkingLot eighthParkingLot = new ParkingLot();
    List<ParkingLot> parkingLotList = Arrays.asList(seventhParkingLot, eighthParkingLot);

    ParkingLotManager parkingLotManager = new ParkingLotManager(managementList, parkingLotList);
    Ticket ticket = parkingLotManager.park(new Car());

    assertNotNull(ticket);
    assertEquals(9, seventhParkingLot.getAvailablePosition());
    assertEquals(10, eighthParkingLot.getAvailablePosition());
  }
}

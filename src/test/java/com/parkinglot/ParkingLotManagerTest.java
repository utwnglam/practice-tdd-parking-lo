package com.parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    ParkingLotManager parkingLotManager = new ParkingLotManager(managementList);
    assertTrue(parkingLotManager.contains(parkingBoy));
  }

//  SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
//  SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy();
}

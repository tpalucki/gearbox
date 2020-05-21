import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GearboxDriverTest {

    static GearboxDriver givenDriver() {
        Gearbox gearbox = new Gearbox();
        ExternalSystemsFacade externalSystems = new ExternalSystemsFacade(new ExternalSystems());
        return new GearboxDriver(gearbox, externalSystems);
    }

//    @Test
//    void handleGearUp() {
//        Driver driver = givenDriver();
//        Gear defaultGear = driver.currentGear();
//        // when
//        driver.handleGearUp();
//        // then
//        Assertions.assertEquals(driver.gearAbove(defaultGear), driver.currentGear());
//    }

//    @Test
//    void gearDown() {
//        Driver driver = givenDriver();
//        Gear defaultGear = driver.currentGear();
//        // when
//        driver.handleGearDown();
//        // then
//        Assertions.assertEquals(driver.gearBelow(defaultGear), driver.currentGear());
//    }

    @Test
    void aggressiveModeUp() {
        Driver driver = givenDriver();
        // when
        driver.shiftAggressiveModeUp();
        //then
        GearboxDriver.AggressiveMode currentAggresiveMode = driver.currentAggressiveMode();
        Assertions.assertEquals(GearboxDriver.AggressiveMode.MODE_2, currentAggresiveMode);
    }

    @Test
    void aggressiveModeUpTwice() {
        Driver driver = givenDriver();
        // when
        driver.shiftAggressiveModeUp();
        driver.shiftAggressiveModeUp();
        //then
        GearboxDriver.AggressiveMode currentAggresiveMode = driver.currentAggressiveMode();
        Assertions.assertEquals(GearboxDriver.AggressiveMode.MODE_3, currentAggresiveMode);
    }

    @Test
    void aggressiveModeUpThreeTimes() {
        Driver driver = givenDriver();
        // when
        driver.shiftAggressiveModeUp();
        driver.shiftAggressiveModeUp();
        driver.shiftAggressiveModeUp();
        //then
        GearboxDriver.AggressiveMode currentAggresiveMode = driver.currentAggressiveMode();
        Assertions.assertEquals(GearboxDriver.AggressiveMode.MODE_3, currentAggresiveMode);
    }

    @Test
    void aggressiveModeUpFourTimes() {
        Driver driver = givenDriver();
        // when
        driver.shiftAggressiveModeUp();
        driver.shiftAggressiveModeUp();
        driver.shiftAggressiveModeUp();
        driver.shiftAggressiveModeUp();
        //then
        GearboxDriver.AggressiveMode currentAggresiveMode = driver.currentAggressiveMode();
        Assertions.assertEquals(GearboxDriver.AggressiveMode.MODE_3, currentAggresiveMode);
    }

    @Test
    void aggressiveModeDown() {
        Driver driver = givenDriver();
        // when
        driver.shiftAggressiveModeDown();
        //then
        GearboxDriver.AggressiveMode currentAggresiveMode = driver.currentAggressiveMode();
        Assertions.assertEquals(GearboxDriver.AggressiveMode.MODE_1, currentAggresiveMode);
    }

    @Test
    void defaultDriveMode() {
        Driver driver = givenDriver();
        // when
        GearboxDriver.DriveMode defaultDriveMode = driver.currentDriveMode();
        // then
        Assertions.assertEquals(GearboxDriver.DriveMode.COMFORT, defaultDriveMode);
    }

    @Test
    void driveModeSwitchedOnce() {
        Driver driver = givenDriver();
        // when
        driver.switchDriveMode();
        // then
        GearboxDriver.DriveMode currentDriveMode = driver.currentDriveMode();
        Assertions.assertEquals(GearboxDriver.DriveMode.SPORT, currentDriveMode);
    }

    @Test
    void driveModeSwitchedTwice() {
        Driver driver = givenDriver();
        // when
        driver.switchDriveMode();
        driver.switchDriveMode();
        // then
        GearboxDriver.DriveMode currentDriveMode = driver.currentDriveMode();
        Assertions.assertEquals(GearboxDriver.DriveMode.ECO, currentDriveMode);
    }

    @Test
    void driveModeSwitchedThreeTimes() {
        Driver driver = givenDriver();
        // when
        driver.switchDriveMode();
        driver.switchDriveMode();
        driver.switchDriveMode();
        // then
        GearboxDriver.DriveMode currentDriveMode = driver.currentDriveMode();
        Assertions.assertEquals(GearboxDriver.DriveMode.COMFORT, currentDriveMode);
    }

}
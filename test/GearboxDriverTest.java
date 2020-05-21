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
        Assertions.assertEquals(GearboxDriver.AggressiveMode.MODE_2, driver.currentAggressiveMode());
    }

    @Test
    void aggressiveModeUpTwice() {
        Driver driver = givenDriver();
        // when
        driver.shiftAggressiveModeUp();
        driver.shiftAggressiveModeUp();
        //then
        Assertions.assertEquals(GearboxDriver.AggressiveMode.MODE_3, driver.currentAggressiveMode());
    }

    @Test
    void aggressiveModeUpThreeTimes() {
        Driver driver = givenDriver();
        // when
        driver.shiftAggressiveModeUp();
        driver.shiftAggressiveModeUp();
        driver.shiftAggressiveModeUp();
        //then
        Assertions.assertEquals(GearboxDriver.AggressiveMode.MODE_3, driver.currentAggressiveMode());
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
        Assertions.assertEquals(GearboxDriver.AggressiveMode.MODE_3, driver.currentAggressiveMode());
    }

    @Test
    void aggressiveModeDown() {
        Driver driver = givenDriver();
        // when
        driver.shiftAggressiveModeDown();
        //then
        Assertions.assertEquals(GearboxDriver.AggressiveMode.MODE_1, driver.currentAggressiveMode());
    }

    @Test
    void defaultDriveMode() {
        Driver driver = givenDriver();
        // then
        Assertions.assertEquals(GearboxDriver.DriveMode.COMFORT, driver.currentDriveMode());
    }

    @Test
    void driveModeSwitchedOnce() {
        Driver driver = givenDriver();
        // when
        driver.switchDriveMode();
        // then
        Assertions.assertEquals(GearboxDriver.DriveMode.SPORT, driver.currentDriveMode());
    }

    @Test
    void driveModeSwitchedTwice() {
        Driver driver = givenDriver();
        // when
        driver.switchDriveMode();
        driver.switchDriveMode();
        // then
        Assertions.assertEquals(GearboxDriver.DriveMode.ECO, driver.currentDriveMode());
    }

    @Test
    void driveModeSwitchedThreeTimes() {
        Driver driver = givenDriver();
        // when
        driver.switchDriveMode();
        driver.switchDriveMode();
        driver.switchDriveMode();
        // then
        Assertions.assertEquals(GearboxDriver.DriveMode.COMFORT, driver.currentDriveMode());
    }

    @Test
    void manuallyShiftGearBelowAllowedRange() {
        Assertions.fail("Not yet implemented");
    }

}
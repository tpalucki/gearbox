import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import provided.ExternalSystems;
import provided.Gearbox;

class GearboxDriverTest {

    static GearboxDriver givenDriver() {
        Gearbox gearbox = new Gearbox();
        gearbox.setMaxDrive(8);
        gearbox.setCurrentGear(1);
        gearbox.setGearBoxCurrentParams(new Integer[]{1, 1});

        CarDataProvider carDataProvider = Mockito.mock(CarDataProvider.class);

        Mockito.when(carDataProvider.isTrailerConnected()).thenReturn(false);
        Mockito.when(carDataProvider.isBreakPressed()).thenReturn(false);

        ExternalSystemsFacade externalSystems = new ExternalSystemsFacade(new ExternalSystems(), carDataProvider);
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
//
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
        Assertions.assertEquals(DriveMode.COMFORT, driver.currentDriveMode());
    }

    @Test
    void driveModeSwitchedOnce() {
        Driver driver = givenDriver();
        // when
        driver.switchDriveMode();
        // then
        Assertions.assertEquals(DriveMode.SPORT, driver.currentDriveMode());
    }

    @Test
    void driveModeSwitchedTwice() {
        Driver driver = givenDriver();
        // when
        driver.switchDriveMode();
        driver.switchDriveMode();
        // then
        Assertions.assertEquals(DriveMode.ECO, driver.currentDriveMode());
    }

    @Test
    void driveModeSwitchedThreeTimes() {
        Driver driver = givenDriver();
        // when
        driver.switchDriveMode();
        driver.switchDriveMode();
        driver.switchDriveMode();
        // then
        Assertions.assertEquals(DriveMode.COMFORT, driver.currentDriveMode());
    }

    @Test
    void manuallyShiftGearBelowAllowedRange() {
        Driver driver = givenDriver();
        // when
        driver.handleGearDown();
        driver.handleGearDown();
        driver.handleGearDown();
        // then
        Assertions.assertEquals(new Gear(1), driver.currentGear());
    }

    @Test
    void manuallyShiftGearAboveAllowedRange() {
        Driver driver = givenDriver();
        // when
        for (int i = 0; i < 10; i++) {
            driver.handleGearUp();
        }
        // then
        Assertions.assertEquals(new Gear(8), driver.currentGear());
    }


    // COMFORT - przy 0.5 nacisniecia gazu jeszcze nie kickdown
    // COMFORT - 4500 czy zrzucic bieg w kickdown

    // SPORT - obroty 1500+ i sprzucamy bieg
    // SPORT - nacisniecie 0,5- obroty 5000+ zrzuczamy 1 bieg
    // SPORT - nacisniecie 0,7- obroty 5000+ zrzucamy 2 biegi
}
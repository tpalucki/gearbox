import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import provided.ExternalSystems;
import provided.Gearbox;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void setAggressiveMode2() {
        Driver driver = givenDriver();
        // when
        AggressiveMode mode = driver.switchAggressiveMode(AggressiveMode.MODE_2);
        //then
        assertEquals(AggressiveMode.MODE_2, driver.currentAggressiveMode());
        assertEquals(AggressiveMode.MODE_2, mode);
    }

    @Test
    void setAggressiveMode3() {
        Driver driver = givenDriver();
        // when
        AggressiveMode mode = driver.switchAggressiveMode(AggressiveMode.MODE_3);
        //then
        assertEquals(AggressiveMode.MODE_3, driver.currentAggressiveMode());
        assertEquals(AggressiveMode.MODE_3, mode);
    }

    @Test
    void setAggressiveMode1() {
        Driver driver = givenDriver();
        // when
        AggressiveMode mode = driver.switchAggressiveMode(AggressiveMode.MODE_1);
        //then
        assertEquals(AggressiveMode.MODE_1, driver.currentAggressiveMode());
        assertEquals(AggressiveMode.MODE_1, mode);
    }

    @Test
    void defaultDriveMode() {
        Driver driver = givenDriver();
        // then
        assertEquals(DriveMode.COMFORT, driver.currentDriveMode());
    }

    @Test
    void driveModeSwitchedOnce() {
        Driver driver = givenDriver();
        // when
        driver.switchDriveMode(DriveMode.SPORT);
        // then
        assertEquals(DriveMode.SPORT, driver.currentDriveMode());
    }

    @Test
    void manuallyShiftGearAboveAllowedRange() {
        Driver driver = givenDriver();
        // when
        for (int i = 0; i < 10; i++) {
            driver.shiftGear(ShiftGearDirection.UP);
        }
        // then
        assertEquals(8, driver.currentGear().asInt());
    }


    // COMFORT - przy 0.5 nacisniecia gazu jeszcze nie kickdown
    // COMFORT - 4500 czy zrzucic bieg w kickdown

    // SPORT - obroty 1500+ i sprzucamy bieg
    // SPORT - nacisniecie 0,5- obroty 5000+ zrzuczamy 1 bieg
    // SPORT - nacisniecie 0,7- obroty 5000+ zrzucamy 2 biegi
}
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

    @Test
    void shouldShiftGearUp() {
        Driver driver = givenDriver();
        Gear defaultGear = driver.currentGear();
        // when
        driver.shiftGear(ShiftGearDirection.UP);
        // then
        assertEquals(defaultGear.asInt() + 1, driver.currentGear().asInt());
    }

    @Test
    void shouldShiftGearUpTwice() {
        Driver driver = givenDriver();
        Gear defaultGear = driver.currentGear();
        // when
        driver.shiftGear(ShiftGearDirection.UP);
        driver.shiftGear(ShiftGearDirection.UP);
        // then
        assertEquals(defaultGear.asInt() + 2, driver.currentGear().asInt());
    }

    @Test
    void shouldShiftGearDown() {
        Driver driver = givenDriver();
        driver.shiftGear(ShiftGearDirection.UP);
        driver.shiftGear(ShiftGearDirection.UP);
        Gear defaultGear = driver.currentGear();
        // when
        driver.shiftGear(ShiftGearDirection.DOWN);
        // then
        assertEquals(defaultGear.asInt() - 1, driver.currentGear().asInt());
    }

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

}
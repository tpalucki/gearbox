import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import provided.ExternalSystems;
import provided.Gearbox;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GearboxDriverTest {

    private static final int MAX_DRIVE = 8;

    private Gearbox gearbox;
    private CarDataProvider carDataProvider;
    private ExternalSystemsFacade externalSystemsFacade;
    private ExternalSystems externalSystems;

    @BeforeEach
    void setupTest() {
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

    @Test
    void handleGasTest() {
        Driver driver = givenDriver();
        gearbox.setCurrentGear(2);
        externalSystems.setCurrentRpm(5000);

        // when
        driver.handleGas();

        // then
        assertEquals(3, driver.currentGear().asInt());
    }

    @Test
    void handleGasWithMaxDriveTest() {
        Driver driver = givenDriver();
        gearbox.setCurrentGear(MAX_DRIVE);
        externalSystems.setCurrentRpm(5000);
        // when
        driver.handleGas();
        // then
        assertEquals(8, driver.currentGear().asInt());
    }

    @Test
    void handleGasWithOneBelowMaxDriveTest() {
        Driver driver = givenDriver();
        gearbox.setCurrentGear(MAX_DRIVE - 1);
        externalSystems.setCurrentRpm(5000);
        // when
        driver.handleGas();
        // then
        assertEquals(8, driver.currentGear().asInt());
    }

    @Test
    void handleGasWithMinGearTest() {
        Driver driver = givenDriver();
        gearbox.setCurrentGear(1);
        externalSystems.setCurrentRpm(1000);
        // when
        driver.handleGas();
        // then
        assertEquals(1, driver.currentGear().asInt());
    }

    @Test
    void handleGasWithKickdownOnComfortModeTest() {
        Driver driver = givenDriver();
        gearbox.setCurrentGear(5);
        externalSystems.setCurrentRpm(4000);
        Mockito.when(carDataProvider.currentGasPosition()).thenReturn(0.1d);
        // when
        driver.handleGas();
        // then
        assertEquals(4, driver.currentGear().asInt());
    }

    @Test
    void handleGasWithoutKickdownBecauseOfRPMsAboveRangeOnComfortModeTest() {
        Driver driver = givenDriver();
        gearbox.setCurrentGear(5);
        externalSystems.setCurrentRpm(6000);
        Mockito.when(carDataProvider.currentGasPosition()).thenReturn(0.1d);
        // when
        driver.handleGas();
        // then
        assertEquals(5, driver.currentGear().asInt());
    }

    @Test
    void handleGasWithoutKickdownBecauseOfRPMsAboveRangeOnSportModeTest() {
        Driver driver = givenDriver();
        gearbox.setCurrentGear(5);
        externalSystems.setCurrentRpm(6000);
        Mockito.when(carDataProvider.currentGasPosition()).thenReturn(0.1d);
        // when
        driver.handleGas();
        // then
        assertEquals(5, driver.currentGear().asInt());
    }

    @Test
    void lightKickdownOnSportMode() {
        Driver driver = givenSportModeDriver();
        Mockito.when(carDataProvider.currentGasPosition()).thenReturn(0.6d);
        // when
        driver.handleGas();
        // then
        assertEquals(4, driver.currentGear().asInt());
    }

    @Test
    void strongKickdownOnSportMode() {
        Driver driver = givenSportModeDriver();
        Mockito.when(carDataProvider.currentGasPosition()).thenReturn(0.2d);
        // when
        driver.handleGas();
        // then
        assertEquals(3, driver.currentGear().asInt());
    }

    @Test
    void dynamicMode() {
        Driver driver = givenDynamicModeDriver();
        externalSystems.setAngularSpeed(100d);
        Gear defaultGear = driver.currentGear();
        // when
        driver.handleGas();
        // then
        assertEquals(defaultGear.asInt(), driver.currentGear().asInt());
    }

    private GearboxDriver givenDriver() {
        gearbox = new Gearbox();
        gearbox.setMaxDrive(MAX_DRIVE);
        gearbox.setCurrentGear(1);
        gearbox.setGearBoxCurrentParams(new Integer[]{1, 1});

        carDataProvider = Mockito.mock(CarDataProvider.class);

        Mockito.when(carDataProvider.isTrailerConnected()).thenReturn(false);
        Mockito.when(carDataProvider.isBreakPressed()).thenReturn(false);
        Mockito.when(carDataProvider.currentGasPosition()).thenReturn(0.9d);

        externalSystems = new ExternalSystems();
        externalSystemsFacade = new ExternalSystemsFacade(externalSystems, carDataProvider);
        return new GearboxDriver(gearbox, externalSystemsFacade);
    }

    private Driver givenSportModeDriver() {
        Driver driver = givenDriver();
        driver.switchDriveMode(DriveMode.SPORT);
        gearbox.setCurrentGear(5);
        externalSystems.setCurrentRpm(4000);
        return driver;
    }

    private Driver givenDynamicModeDriver() {
        Driver driver = givenDriver();
        driver.toggleDynamicMode();
        gearbox.setCurrentGear(5);
        externalSystems.setCurrentRpm(4000);
        return driver;
    }
}
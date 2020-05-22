import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GearCalculatorTest {

    private final Gear min = new Gear(1);
    private final Gear max = new Gear(8);
    private final GearRange defaultGearRange = new GearRange(min, max);

    private GearCalculator givenGearCalculator() {
        return new GearCalculator(defaultGearRange);
    }

    @BeforeEach
    void prepareTest() {

    }

    @Test
    void calculatePrevious() {
        // given
        GearCalculator calculator = givenGearCalculator();
        RPM rpm = RPM.rpm(500);
        Gear gear = new Gear(2);
        GasPosition gasPosition = GasPosition.of(0.9d);

        // when - then
        Assertions.assertEquals(new Gear(1), calculator.calculate(rpm, gear, gasPosition, DriveMode.ECO));
    }

    @Test
    void calculateNext() {
        // given
        GearCalculator calculator = givenGearCalculator();
        RPM rpm = RPM.rpm(2500);
        Gear gear = new Gear(2);
        GasPosition gasPosition = GasPosition.of(0.9d);

        // when - then
        Assertions.assertEquals(new Gear(3), calculator.calculate(rpm, gear, gasPosition,  DriveMode.ECO));
    }

    @Test
    void handleMaxGear() {
        // given
        GearCalculator calculator = givenGearCalculator();
        RPM rpm = RPM.rpm(2500);
        GasPosition gasPosition = GasPosition.of(0.9d);

        // when - then
        Assertions.assertEquals(max, calculator.calculate(rpm, max,  gasPosition, DriveMode.ECO));

    }
}
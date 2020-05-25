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

    @Test
    void calculatePreviousInEco() {
        // given
        GearCalculator calculator = givenGearCalculator();
        // when
        Gear result = calculator.calculate(RPM.rpm(500), new Gear(2), GasPosition.of(0.9d), DriveMode.ECO);
        // then
        Assertions.assertEquals(1, result.asInt());
    }

    @Test
    void calculateNextInEco() {
        // given
        GearCalculator calculator = givenGearCalculator();
        RPM rpm = RPM.rpm(2500);
        Gear gear = new Gear(2);
        GasPosition gasPosition = GasPosition.of(0.9d);

        // when - then
        Assertions.assertEquals(new Gear(3), calculator.calculate(rpm, gear, gasPosition, DriveMode.ECO));
    }

    @Test
    void handleMaxGear() {
        // given
        GearCalculator calculator = givenGearCalculator();
        RPM rpm = RPM.rpm(2500);
        GasPosition gasPosition = GasPosition.of(0.9d);

        // when - then
        Assertions.assertEquals(max, calculator.calculate(rpm, max, gasPosition, DriveMode.ECO));
    }

    @Test
    void whenMaxGearAndRpmAboveOptimalShouldCalculateMaxGear() {
        // given
        GearCalculator calculator = givenGearCalculator();
        // when
        Gear result = calculator.calculate(RPM.rpm(50), min, GasPosition.of(0.9d), DriveMode.ECO);
        // then
        Assertions.assertEquals(min, result);
    }

    @Test
    void whenMinialAndRpmBelowOptimalRangeShouldReturnMinimalGear() {
        // given
        GearCalculator calculator = givenGearCalculator();
        // when
        Gear result = calculator.calculate(RPM.rpm(9000), max, GasPosition.of(0.9d), DriveMode.ECO);
        // then
        Assertions.assertEquals(max, result);
    }

    @Test
    void noKickdownOnEcoMode() {
        // given
        GearCalculator calculator = givenGearCalculator();
        // when
        Gear result = calculator.calculate(RPM.rpm(1750), new Gear(5), GasPosition.of(0.1d), DriveMode.ECO);
        // then
        Assertions.assertEquals(new Gear(5), result);
    }

    @Test
    void kickdownOnComfortMode() {
        // given
        GearCalculator calculator = givenGearCalculator();
        // when
        Gear result = calculator.calculate(RPM.rpm(2000), new Gear(5), GasPosition.of(0.45d), DriveMode.COMFORT);
        // then
        Assertions.assertEquals(4, result.asInt());
    }

    @Test
    void noStrongKickdownOnComfortMode() {
        // given
        GearCalculator calculator = givenGearCalculator();
        // when
        Gear result = calculator.calculate(RPM.rpm(2000), new Gear(5), GasPosition.of(0.1d), DriveMode.COMFORT);
        // then
        Assertions.assertEquals(4, result.asInt());

    }


    @Test
    void lightKickdownOnSportMode() {
        // given
        GearCalculator calculator = givenGearCalculator();
        // when
        Gear result = calculator.calculate(RPM.rpm(2000), new Gear(5), GasPosition.of(0.69d), DriveMode.SPORT);
        // then
        Assertions.assertEquals(4, result.asInt());
    }

    @Test
    void strongKickdownOnSportMode() {
        // given
        GearCalculator calculator = givenGearCalculator();
        // when
        Gear result = calculator.calculate(RPM.rpm(2000), new Gear(5), GasPosition.of(0.49d), DriveMode.SPORT);
        // then
        Assertions.assertEquals(3, result.asInt());
    }

    @Test
    void sportModeStrongKickdownWhenSecondGear() {
        // given
        GearCalculator calculator = givenGearCalculator();
        // when
        Gear result = calculator.calculate(RPM.rpm(2000), new Gear(2), GasPosition.of(0.1d), DriveMode.SPORT);
        // then
        Assertions.assertEquals(1, result.asInt());
    }
}
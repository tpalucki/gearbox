import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RPMRangeTest {

    @Test
    void rangeIsAboveRPMShouldReturnTrueWhenRPMBelow() {
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(3));
        RPM rpm = RPM.rpm(500);

        Assertions.assertTrue(range.isAbove(rpm));
    }

    @Test
    void rangeIsAboveRPMShouldReturnFalseWhenRPMInRange() {
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(3));
        RPM rpm = RPM.k(2);

        Assertions.assertFalse(range.isAbove(rpm));
    }


    @Test
    void rangeIsAboveRPMShouldReturnFalseWhenRPMAbove() {
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(3));
        RPM rpm = RPM.k(4);

        Assertions.assertFalse(range.isAbove(rpm));
    }

    @Test
    void rangeIsBelowRPMShouldReturnFalseWhenRPMBelow() {
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(3));
        RPM rpm = RPM.rpm(500);

        Assertions.assertFalse(range.isBelow(rpm));
    }

    @Test
    void rangeIsBelowRPMShouldReturnFalseWhenRPMInRange() {
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(3));
        RPM rpm = RPM.k(2);

        Assertions.assertFalse(range.isBelow(rpm));
    }


    @Test
    void rangeIsBelowRPMShouldReturnTrueWhenRPMAbove() {
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(3));
        RPM rpm = RPM.k(4);

        Assertions.assertTrue(range.isBelow(rpm));
    }

    @Test
    void rangeIncludesRPMShouldReturnTrueWhenRPMInrange() {
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(3));
        RPM rpm = RPM.k(2);

        Assertions.assertTrue(range.includes(rpm));
    }

    @Test
    void rangeIncludesRPMShouldReturnTrueWhenRPMInMinOfRange() {
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(3));
        RPM rpm = RPM.k(1);

        Assertions.assertTrue(range.includes(rpm));
    }

    @Test
    void rangeIncludesRPMShouldReturnTrueWhenRPMInMaxOfrange() {
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(3));
        RPM rpm = RPM.k(3);

        Assertions.assertTrue(range.includes(rpm));
    }
}
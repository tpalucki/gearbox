import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class RPMTest {

    @Test
    void comparisonIsGreaterThanShouldReturnFalse() {
        // given
        RPM rpm1 = RPM.rpm(1000d);
        RPM rpm2 = RPM.rpm(2000d);

        Assertions.assertFalse(rpm1.isGreaterThan(rpm2));
    }

    @Test
    void comparisonIsGreaterThanShouldReturnTrue() {
        // given
        RPM rpm1 = RPM.rpm(2000d);
        RPM rpm2 = RPM.rpm(1000d);

        Assertions.assertTrue(rpm1.isGreaterThan(rpm2));
    }

    @Test
    void comparisonIsLowerThanShouldReturnFalse() {
        // given
        RPM rpm1 = RPM.rpm(2000d);
        RPM rpm2 = RPM.rpm(1000d);

        Assertions.assertFalse(rpm1.isLowerThan(rpm2));
    }

    @Test
    void comparisonIsLowerThanShouldReturnTrue() {
        // given
        RPM rpm1 = RPM.rpm(2000d);
        RPM rpm2 = RPM.rpm(1000d);

        Assertions.assertTrue(rpm2.isLowerThan(rpm1));
    }

    @Test
    void comparingTwoSameWithIsLowerThanShouldReturnFalse() {
        // given
        RPM rpm1 = RPM.rpm(3000d);
        RPM rpm2 = RPM.rpm(3000d);

        Assertions.assertFalse(rpm1.isLowerThan(rpm2));
    }

    @Test
    void comparingTwoSameWithIsGreaterThanShouldReturnFalse() {
        // given
        RPM rpm1 = RPM.rpm(3000d);
        RPM rpm2 = RPM.rpm(3000d);

        Assertions.assertFalse(rpm1.isGreaterThan(rpm2));
    }

    @Test
    void comparisonWithIsAboveShouldReturnTrueIfRPMIsAbove() {
        // given
        RPM rpm = RPM.rpm(5000d);
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(4));

        Assertions.assertTrue(rpm.isAbove(range));
    }

    @Test
    void comparisonWithIsAboveShouldReturnFalseIfRPMIsInRange() {
        // given
        RPM rpm = RPM.rpm(3000d);
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(4));

        Assertions.assertFalse(rpm.isAbove(range));
    }

    @Test
    void comparisonWithIsAboveShouldReturnFalseIfRPMIsBelowRange() {
        // given
        RPM rpm = RPM.rpm(500d);
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(4));

        Assertions.assertFalse(rpm.isAbove(range));
    }

    @Test
    void comparisonWithIsAboveShouldReturnFalseIfRPMIsEqualMaxOfRange() {
        // given
        RPM rpm = RPM.k(4d);
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(4));

        Assertions.assertFalse(rpm.isAbove(range));
    }

    ///

    @Test
    void comparisonWithIsBelowShouldReturnFalseIfRPMIsAbove() {
        // given
        RPM rpm = RPM.rpm(5000d);
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(4));

        Assertions.assertFalse(rpm.isBelow(range));
    }

    @Test
    void comparisonWithIsBelowShouldReturnFalseIfRPMIsInRange() {
        // given
        RPM rpm = RPM.rpm(3000d);
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(4));

        Assertions.assertFalse(rpm.isBelow(range));
    }

    @Test
    void comparisonWithIsBelowShouldReturnTrueIfRPMIsBelowRange() {
        // given
        RPM rpm = RPM.rpm(500d);
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(4));

        Assertions.assertTrue(rpm.isBelow(range));
    }

    @Test
    void comparisonWithIsBelowShouldReturnFalseIfRPMIsEqualMinOfRange() {
        // given
        RPM rpm = RPM.k(1d);
        RPMRange range = new RPMRange(RPM.k(1), RPM.k(4));

        Assertions.assertFalse(rpm.isAbove(range));
    }

    @Test
    void rpmCreatedWithDefferentStaticFactoryMethodShouldBeEqual() {
        RPM rpm1 = RPM.rpm(2000d);
        RPM rpm2 = RPM.k(2d);

        Assertions.assertEquals(rpm1, rpm2);
    }

    @Test
    void rpmCreatedWithDefferentStaticFactoryMethodShouldBeEqual2() {
        RPM rpm1 = RPM.rpm(2000d);
        RPM rpm2 = RPM.k(2d);

        Assertions.assertEquals(rpm2, rpm1);
    }

    @Test
    void shouldNotBePossibleToCreateRPMLowerThanZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> RPM.k(-1d));
    }

    @Test
    void shouldNotBePossibleToCreateRPMLowerThanZeroWithDifferentMethod() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> RPM.rpm(-500d));
    }

    @Test
    void shouldBePossibleToCreateRPMEqualZero() {
        Assertions.assertNotNull(RPM.rpm(0));
    }
}
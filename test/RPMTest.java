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
}
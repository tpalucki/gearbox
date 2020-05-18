import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GearTest {

    @Test
    void shouldNotCreateWithGearLowerThanOne() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gear(-1));
    }

    @Test
    void shouldNotCreateWithGearEqualsZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Gear(0));
    }

    @Test
    void shouldCreateWithGearAboveZero() {
        Assertions.assertDoesNotThrow(() -> new Gear(1));
    }

    @Test
    void isGreaterThan() {
        // given
        Gear one = new Gear(1);
        Gear two = new Gear(2);

        // when - then
        Assertions.assertTrue(two.isGreaterThan(one));
    }

    @Test
    void isLowerThan() {
        // given
        Gear one = new Gear(1);
        Gear two = new Gear(2);

        // when - then
        Assertions.assertTrue(one.isLowerThan(two));
    }

    @Test
    void isNotGreaterThan() {
        // given
        Gear one = new Gear(1);
        Gear two = new Gear(2);

        // when - then
        Assertions.assertFalse(one.isGreaterThan(two));
    }

    @Test
    void isNotLowerThan() {
        // given
        Gear one = new Gear(1);
        Gear two = new Gear(2);

        // when - then
        Assertions.assertFalse(two.isLowerThan(one));
    }

}
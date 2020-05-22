import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GasPositionTest {

    @Test
    void shouldCreate() {
        assertNotNull(GasPosition.of(0.1));
    }

    @Test
    void shouldCreateWithZero() {
        assertNotNull(GasPosition.of(0));
    }

    @Test
    void shouldCreateWithOne() {
        assertNotNull(GasPosition.of(1));
    }

    @Test
    void shouldNotCreateBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> GasPosition.of(-1d));
    }

    @Test
    void shouldNotCreateAboveOne() {
        assertThrows(IllegalArgumentException.class, () -> GasPosition.of(1.1d));
    }

    @Test
    void shouldNotCreateMuchAboveOne() {
        assertThrows(IllegalArgumentException.class, () -> GasPosition.of(10d));
    }

    @Test
    void isLowerThanShouldBeTrue() {
        GasPosition position1 = GasPosition.of(0.1);
        GasPosition position2 = GasPosition.of(0.5);

        assertTrue(position1.isLowerThan(position2));
    }

    @Test
    void isLowerThanShouldBeFalse() {
        GasPosition position1 = GasPosition.of(0.5);
        GasPosition position2 = GasPosition.of(0.1);

        assertFalse(position1.isLowerThan(position2));
    }

    @Test
    void isLowerThanShouldBeFalseWhenEquals() {
        GasPosition position1 = GasPosition.of(0.5);
        GasPosition position2 = GasPosition.of(0.5);

        assertFalse(position1.isLowerThan(position2));
    }
}
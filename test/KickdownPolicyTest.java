import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KickdownPolicyTest {

    private KickdownPolicy kickdownPolicy = new KickdownPolicy(GasPosition.of(0.5d), RPM.k(3d));

    @Test
    void shoudBeApplicable() {
        assertTrue(kickdownPolicy.isApplicable(GasPosition.of(0.1d)));
    }

    @Test
    void shouldNotBeApplicableWhenGasPositionEqualsRange() {
        assertFalse(kickdownPolicy.isApplicable(GasPosition.of(0.5d)));
    }

    @Test
    void shouldNotBeApplicableWhenGasPositionAboveRange() {
        assertFalse(kickdownPolicy.isApplicable(GasPosition.of(0.6d)));
    }

}
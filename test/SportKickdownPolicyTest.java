import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SportKickdownPolicyTest {

    private KickdownPolicy kickdownPolicy = new SportKickdownPolicy(GasPosition.of(0.5d), RPM.k(5d), GasPosition.of(0.7d), RPM.k(5d));

    @Test
    void shouldNotBeApplicableWhenGasPositionAboveRange() {
        assertFalse(kickdownPolicy.isApplicable(GasPosition.of(0.8d)));
    }

    @Test
    void shouldNotBeApplicableWhenGasPositionEqualsRange() {
        assertFalse(kickdownPolicy.isApplicable(GasPosition.of(0.7d)));
    }

    @Test
    void shoudBeApplicableOnStrongKickdown() {
        assertTrue(kickdownPolicy.isApplicable(GasPosition.of(0.1d)));
    }

    @Test
    void shouldBeApplicableWhenLightKickdown() {
        assertTrue(kickdownPolicy.isApplicable(GasPosition.of(0.69d)));
    }


    @Test
    void shouldBeApplicableWhenGasPositionEqualsLowerThreshold() {
        assertTrue(kickdownPolicy.isApplicable(GasPosition.of(0.5d)));
    }

}
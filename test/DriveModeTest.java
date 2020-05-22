import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriveModeTest {

    @Test
    void ecoDriveModeShouldHavePolicyAndRange() {
        //given
        DriveMode driveMode = DriveMode.ECO;

        // then
        assertNotNull(driveMode.optimalRPMRange());
        assertNotNull(driveMode.kickdownPolicy());
    }

    @Test
    void comfortDriveModeShouldHavePolicyAndRange() {
        //given
        DriveMode driveMode = DriveMode.COMFORT;

        // then
        assertNotNull(driveMode.optimalRPMRange());
        assertNotNull(driveMode.kickdownPolicy());
    }

    @Test
    void sportDriveModeShouldHavePolicyAndRange() {
        //given
        DriveMode driveMode = DriveMode.SPORT;

        // then
        assertNotNull(driveMode.optimalRPMRange());
        assertNotNull(driveMode.kickdownPolicy());
    }

    @Test
    void sportDriveModeShouldHaveProperPolicyType() {
        //given
        DriveMode driveMode = DriveMode.SPORT;

        // then
        assertTrue(driveMode.kickdownPolicy() instanceof SportModeKickdownPolicy);
    }

    @Test
    void comfortDriveModeShouldHaveProperPolicyType() {
        //given
        DriveMode driveMode = DriveMode.COMFORT;

        // then
        assertTrue(driveMode.kickdownPolicy() instanceof KickdownPolicy);
    }

    @Test
    void ecoDriveModeShouldHaveProperPolicyType() {
        //given
        DriveMode driveMode = DriveMode.ECO;

        // then
        assertTrue(driveMode.kickdownPolicy() instanceof KickdownPolicy);
    }
}
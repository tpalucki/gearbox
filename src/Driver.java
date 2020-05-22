
interface Driver {

    void handleGearUp();

    void handleGearDown();

    void handleGas();

    GearboxDriver.AggressiveMode shiftAggressiveModeUp();

    GearboxDriver.AggressiveMode shiftAggressiveModeDown();

    GearboxDriver.AggressiveMode currentAggressiveMode();

    DriveMode switchDriveMode();

    DriveMode currentDriveMode();

    boolean toggleDynamicMode();

    Gear currentGear();

}
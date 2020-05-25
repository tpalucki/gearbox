
interface Driver {

    void shiftGear(ShiftGearDirection direction);

    void handleGas();

    GearboxDriver.AggressiveMode shiftAggressiveModeUp();

    GearboxDriver.AggressiveMode shiftAggressiveModeDown();

    GearboxDriver.AggressiveMode currentAggressiveMode();

    DriveMode currentDriveMode();

    DriveMode switchDriveMode(DriveMode driveMode);

    boolean toggleDynamicMode();

    Gear currentGear();

}
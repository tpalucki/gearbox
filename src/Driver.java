
interface Driver {

    void handleGearUp();

    void handleGearDown();

    void handleGas();

    GearboxDriver.AggressiveMode shiftAggressiveModeUp();

    GearboxDriver.AggressiveMode shiftAggressiveModeDown();

    GearboxDriver.AggressiveMode currentAggressiveMode();

    GearboxDriver.DriveMode switchDriveMode();

    GearboxDriver.DriveMode currentDriveMode();

    boolean toggleDynamicMode();

    Gear currentGear();

}
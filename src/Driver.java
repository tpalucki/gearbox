
interface Driver {

    void shiftGear(ShiftGearDirection direction);

    Gear currentGear();

    void handleGas();

    AggressiveMode switchAggressiveMode(AggressiveMode mode);

    AggressiveMode currentAggressiveMode();

    DriveMode currentDriveMode();

    DriveMode switchDriveMode(DriveMode driveMode);

    boolean toggleDynamicMode();
}
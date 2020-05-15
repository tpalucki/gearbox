
interface Driver {

    void handleGearUp();

    void handleGearDown();

    GearboxDriver.AggressiveMode shiftAggressiveModeUp();

    GearboxDriver.AggressiveMode shiftAggressiveModeDown();

    GearboxDriver.AggressiveMode currentAggressiveMode();

    GearboxDriver.DriveMode switchDriveMode();

    GearboxDriver.DriveMode currentDriveMode();

    Gear currentGear();

    Gear gearAbove(Gear defaultGear);

    Gear gearBelow(Gear defaultGear);
}
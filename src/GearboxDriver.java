class GearboxDriver implements Driver {
    private static final GearRange DEFAULT_GEAR_RANGE = new GearRange(new Gear(1), new Gear(8));

    private final GearCalculator gearCalculator;
    private final ExternalSystemsFacade externalSystems;
    private final GearboxACL gearbox;

    private AggressiveMode aggressiveMode;
    private DriveMode driveMode;

    enum AggressiveMode {
        MODE_1, MODE_2, MODE_3
    }

    enum DriveMode {
        ECO(new RPMRange(RPM.k(1), RPM.k(2))),
        COMFORT(new RPMRange(RPM.k(1), RPM.rpm(2500))),
        SPORT(new RPMRange(RPM.k(1), RPM.k(2500)));

        private final RPMRange associatedRange;

        DriveMode(RPMRange range) {
            this.associatedRange = range;
        }

        RPMRange asRPMRange() {
            return associatedRange;
        }
    }

    GearboxDriver(Gearbox gearbox, ExternalSystemsFacade externalSystems) {
        this.gearbox = new GearboxACL(gearbox);
        this.externalSystems = externalSystems;
        driveMode = DriveMode.COMFORT;
        aggressiveMode = AggressiveMode.MODE_1;
        gearCalculator = new GearCalculator(DEFAULT_GEAR_RANGE);
    }

    @Override
    public void handleGearUp() {

    }

    @Override
    public void handleGearDown() {

    }

    public void handleGas() {
        RPM currentRPM = externalSystems.currentRPM();

        if (gearbox.drive()) {
            Gear newGear = gearCalculator.calculate(currentRPM, currentGear(), driveMode.asRPMRange());
            gearbox.setGear(newGear);
        }
    }

    @Override
    public AggressiveMode shiftAggressiveModeUp() {
        switch (aggressiveMode) {
            case MODE_1:
                aggressiveMode = AggressiveMode.MODE_2;
                break;
            case MODE_2:
                aggressiveMode = AggressiveMode.MODE_3;
                break;
            default:
                break;
        }
        return aggressiveMode;
    }

    @Override
    public AggressiveMode shiftAggressiveModeDown() {
        switch (aggressiveMode) {
            case MODE_3:
                aggressiveMode = AggressiveMode.MODE_2;
                break;
            case MODE_2:
                aggressiveMode = AggressiveMode.MODE_1;
                break;
        }
        return aggressiveMode;
    }

    @Override
    public AggressiveMode currentAggressiveMode() {
        return aggressiveMode;
    }

    @Override
    public DriveMode switchDriveMode() {
        switch (driveMode) {
            case SPORT:
                return driveMode = DriveMode.ECO;
            case COMFORT:
                return driveMode = DriveMode.SPORT;
            default:
                return driveMode = DriveMode.COMFORT;
        }
    }

    @Override
    public Gear currentGear() {
        return gearbox.currentGear();
    }

    @Override
    public DriveMode currentDriveMode() {
        return driveMode;
    }
}

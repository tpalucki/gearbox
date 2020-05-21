class GearboxDriver implements Driver {
    private static final GearRange DEFAULT_GEAR_RANGE = new GearRange(new Gear(1), new Gear(8));
    private static final RPMRange DEFAULT_RPM_RANGE = new RPMRange(RPM.k(1), RPM.k(2));
    private final GearCalculator gearCalculator;
    private AggressiveMode aggresiveMode;
    private DriveMode driveMode;
    private final ExternalSystemsFacade externalSystems;
    private final GearboxACL gearbox;

    enum AggressiveMode {
        MODE_1, MODE_2, MODE_3
    }

    enum DriveMode {
        ECO, COMFORT, SPORT
    }

    GearboxDriver(Gearbox gearbox, ExternalSystemsFacade externalSystems) {
        this.gearbox = new GearboxACL(gearbox);
        this.externalSystems = externalSystems;
        driveMode = DriveMode.COMFORT;
        aggresiveMode = AggressiveMode.MODE_1;
        gearCalculator = new GearCalculator(DEFAULT_GEAR_RANGE, DEFAULT_RPM_RANGE);
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
            Gear newGear = gearCalculator.calculate(currentRPM, currentGear());
            gearbox.setGear(newGear);
        }
    }

    @Override
    public AggressiveMode shiftAggressiveModeUp() {
        switch (aggresiveMode) {
            case MODE_1:
                aggresiveMode = AggressiveMode.MODE_2;
                break;
            case MODE_2:
                aggresiveMode = AggressiveMode.MODE_3;
                break;
            default:
                break;
        }
        return aggresiveMode;
    }

    @Override
    public AggressiveMode shiftAggressiveModeDown() {
        switch (aggresiveMode) {
            case MODE_3:
                aggresiveMode = AggressiveMode.MODE_2;
                break;
            case MODE_2:
                aggresiveMode = AggressiveMode.MODE_1;
                break;
        }
        return aggresiveMode;
    }

    @Override
    public AggressiveMode currentAggressiveMode() {
        return aggresiveMode;
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

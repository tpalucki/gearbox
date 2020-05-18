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
        this.driveMode = DriveMode.COMFORT;
        this.aggresiveMode = AggressiveMode.MODE_1;
        this.gearCalculator = new GearCalculator(DEFAULT_GEAR_RANGE, DEFAULT_RPM_RANGE);
    }

    @Override
    public void handleGearUp() {

    }

    @Override
    public void handleGearDown() {

    }

    public void handleGas() {
        RPM currentRPM = this.externalSystems.currentRPM();

        if (this.gearbox.drive()) {
            Gear newGear = this.gearCalculator.calculate(currentRPM, currentGear());
            this.gearbox.setGear(newGear);
        }
    }

    @Override
    public AggressiveMode shiftAggressiveModeUp() {
        switch (this.aggresiveMode) {
            case MODE_1:
                this.aggresiveMode = AggressiveMode.MODE_2;
                break;
            case MODE_2:
                this.aggresiveMode = AggressiveMode.MODE_3;
                break;
            default:
                break;
        }
        return this.aggresiveMode;
    }

    @Override
    public AggressiveMode shiftAggressiveModeDown() {
        switch (this.aggresiveMode) {
            case MODE_3:
                this.aggresiveMode = AggressiveMode.MODE_2;
                break;
            case MODE_2:
                this.aggresiveMode = AggressiveMode.MODE_1;
                break;
        }
        return this.aggresiveMode;
    }

    @Override
    public AggressiveMode currentAggressiveMode() {
        return this.aggresiveMode;
    }

    @Override
    public DriveMode switchDriveMode() {
        switch (this.driveMode) {
            case SPORT:
                return (this.driveMode = DriveMode.ECO);
            case COMFORT:
                return (this.driveMode = DriveMode.SPORT);
            default:
                return (this.driveMode = DriveMode.COMFORT);
        }
    }

    @Override
    public Gear currentGear() {
        return this.gearbox.currentGear();
    }

    @Override
    public DriveMode currentDriveMode() {
        return this.driveMode;
    }
}

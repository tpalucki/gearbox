class GearboxDriver implements Driver {

    private AggressiveMode aggresiveMode;
    private DriveMode driveMode;
    private final ExternalSystemsFacade externalSystems;
    private final Gearbox gearbox;

    enum AggressiveMode {
        MODE_1, MODE_2, MODE_3
    }

    enum DriveMode {
        ECO, COMFORT, SPORT
    }

    GearboxDriver(Gearbox gearbox, ExternalSystemsFacade externalSystems) {
        this.gearbox = gearbox;
        this.externalSystems = externalSystems;
        this.driveMode = DriveMode.COMFORT;
        this.aggresiveMode = AggressiveMode.MODE_1;
    }

    @Override
    public void handleGearUp() {

    }

    @Override
    public void handleGearDown() {

    }

    public void calculate() {
        RPM currentRPM = this.externalSystems.currentRPM();
        // TODO implement logic
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
        int gearValue = (int) this.gearbox.getCurrentGear();
        return new Gear(Gear.GearEnum.GEAR_1); // TODO add facade for Gearbox
    }

    @Override
    public Gear gearAbove(Gear defaultGear) {
        int gearValue = (int) this.gearbox.getCurrentGear();
        return new Gear(Gear.GearEnum.GEAR_2); // TODO add facade for Gearbox
    }

    @Override
    public Gear gearBelow(Gear defaultGear) {
        int gearValue = (int) this.gearbox.getCurrentGear();
        return new Gear(Gear.GearEnum.GEAR_NEUTRAL); // TODO add facade for Gearbox
    }

    @Override
    public DriveMode currentDriveMode() {
        return this.driveMode;
    }
}

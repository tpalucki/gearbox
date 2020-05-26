import provided.Gearbox;

class GearboxDriver implements Driver {
    private final GearCalculator gearCalculator;
    private final ExternalSystemsFacade externalSystems;
    private final GearboxFacade gearbox;

    private AggressiveMode aggressiveMode;
    private DriveMode driveMode;
    private GearRange gearRange;
    private boolean dynamicMode;

    GearboxDriver(Gearbox gearbox, ExternalSystemsFacade externalSystems) {
        this.gearbox = new GearboxFacade(gearbox);
        this.externalSystems = externalSystems;
        driveMode = DriveMode.COMFORT;
        aggressiveMode = AggressiveMode.MODE_1;
        gearRange = new GearRange(new Gear(1), new Gear(gearbox.getMaxDrive()));
        gearCalculator = new GearCalculator(gearRange);
    }

    @Override
    public void shiftGear(ShiftGearDirection command) {
        switch (command) {
            case UP:
                gearbox.setGear(gearRange.next(gearbox.currentGear()));
                break;
            case DOWN:
                gearbox.setGear(gearRange.previous(gearbox.currentGear()));
                break;
        }
    }

    @Override
    public void handleGas() {
        if (gearbox.isDrive()) {
            if (externalSystems.isTrailerConnected() && externalSystems.isTiltDown()) {
                if (driveMode.optimalRPMRange().includes(externalSystems.currentRPM())) {
                    gearbox.setGear(gearRange.previous(gearbox.currentGear()));
                }
            }

            if (dynamicMode && externalSystems.isGlide()) {
                return;
            }

            Gear newGear = gearCalculator.calculate(externalSystems.currentRPM(), currentGear(),
                    externalSystems.gasPosition(), driveMode);
            gearbox.setGear(newGear);
        }
    }

    @Override
    public AggressiveMode switchAggressiveMode(AggressiveMode mode) {
        return aggressiveMode = mode;
    }


    @Override
    public AggressiveMode currentAggressiveMode() {
        return aggressiveMode;
    }

    @Override
    public DriveMode switchDriveMode(DriveMode driveMode) {
        return this.driveMode = driveMode;
    }

    @Override
    public DriveMode currentDriveMode() {
        return driveMode;
    }

    @Override
    public Gear currentGear() {
        return gearbox.currentGear();
    }

    @Override
    public boolean toggleDynamicMode() {
        return dynamicMode = !dynamicMode;
    }
}

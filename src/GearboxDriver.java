import java.util.List;

class GearboxDriver implements Driver {
    private final GearCalculator gearCalculator;
    private final ExternalSystemsFacade externalSystems;
    private final GearboxFacade gearbox;

    private AggressiveMode aggressiveMode;
    private DriveMode driveMode;
    private GearRange gearRange;
    private boolean dynamicMode;

    enum AggressiveMode {
        MODE_1, MODE_2, MODE_3
    }

    enum DriveMode {
        ECO(new RPMRange(RPM.k(1), RPM.k(2)), KickdownPolicy.NONE),
        COMFORT(new RPMRange(RPM.k(1), RPM.k(2.5)), KickdownPolicy.fromThresholds(List.of(PedalPosition.of(0.5d)))),
        SPORT(new RPMRange(RPM.rpm(1.5), RPM.k(5)), KickdownPolicy.fromThresholds(List.of(PedalPosition.of(0.7d), PedalPosition.of(0.5d))));

        private final RPMRange associatedRange;
        private final KickdownPolicy kickdownPolicy;


        DriveMode(RPMRange range, KickdownPolicy policy) {
            associatedRange = range;
            kickdownPolicy = policy;
        }

        RPMRange optimalRPMRange() {
            return associatedRange;
        }

        public KickdownPolicy kickdownPolicy() {
            return kickdownPolicy;
        }
    }

    GearboxDriver(Gearbox gearbox, ExternalSystemsFacade externalSystems) {
        this.gearbox = new GearboxFacade(gearbox);
        this.externalSystems = externalSystems;
        driveMode = DriveMode.COMFORT;
        aggressiveMode = AggressiveMode.MODE_1;
        gearRange = new GearRange(new Gear(1), new Gear(gearbox.getMaxDrive()));
        gearCalculator = new GearCalculator(gearRange);
    }

    @Override
    public void handleGearUp() {
        gearbox.setGear(gearRange.next(gearbox.currentGear()));
    }

    @Override
    public void handleGearDown() {
        gearbox.setGear(gearRange.previous(gearbox.currentGear()));
    }

    @Override
    public void handleGas() {
        if (gearbox.isDrive()) {
            if (externalSystems.isTrailerConnected() && externalSystems.isTiltDown()) {
                if (driveMode.optimalRPMRange().contains(externalSystems.currentRPM())) {
                    gearbox.setGear(gearRange.previous(gearbox.currentGear()));
                }
            }

            if (dynamicMode && externalSystems.isGlide()) {
                return;
            }
            PedalPosition gasPosition = externalSystems.gasPosition();
            Gear newGear;
            if (driveMode.kickdownPolicy().isApplicable(gasPosition)) {
                // kickdown
                newGear = driveMode.kickdownPolicy().apply(gasPosition, gearbox.currentGear(), gearRange);
            } else {
                // default calculation
                newGear = gearCalculator.calculate(externalSystems.currentRPM(), currentGear(), driveMode.optimalRPMRange());
            }
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

    @Override
    public boolean toggleDynamicMode() {
        return dynamicMode = !dynamicMode;
    }
}

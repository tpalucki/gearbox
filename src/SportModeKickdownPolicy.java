class SportModeKickdownPolicy extends KickdownPolicy {

    private final GasPosition higherGasThreshold;
    private final RPM higherRpmThreshold;

    SportModeKickdownPolicy(GasPosition gasThreshold, RPM rpmThreshold, GasPosition higherGasThreshold, RPM higherRpmThreshold) {
        super(gasThreshold, rpmThreshold);
        this.higherGasThreshold = higherGasThreshold;
        this.higherRpmThreshold = higherRpmThreshold;
    }

    @Override
    boolean isApplicable(GasPosition gasPosition, RPM rpm) {
        return super.isApplicable(gasPosition, rpm) || gasPosition.isLowerThan(higherGasThreshold) && rpm.isLowerThan(higherRpmThreshold);
    }

    @Override
    Gear apply(GasPosition gasPosition, RPM currentRPM, Gear currentGear, GearRange gearRange) {
        Gear gear = super.apply(gasPosition, currentRPM, currentGear, gearRange);
        if (gasPosition.isLowerThan(higherGasThreshold) && currentRPM.isLowerThan(higherRpmThreshold)) {
            gear = gearRange.previous(gear);
        }
        return gear;
    }
}

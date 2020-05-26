class SportKickdownPolicy extends KickdownPolicy {

    private final GasPosition higherGasThreshold;
    private final RPM higherRpmThreshold;

    SportKickdownPolicy(GasPosition gasThreshold, RPM rpmThreshold, GasPosition higherGasThreshold, RPM higherRpmThreshold) {
        super(gasThreshold, rpmThreshold);
        this.higherGasThreshold = higherGasThreshold;
        this.higherRpmThreshold = higherRpmThreshold;
    }

    @Override
    boolean isApplicable(GasPosition gasPosition) {
        return super.isApplicable(gasPosition) || gasPosition.isLowerThan(higherGasThreshold);
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

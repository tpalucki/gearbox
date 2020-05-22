class KickdownPolicy {

    static final KickdownPolicy NO_KICKDOWN = new KickdownPolicy(GasPosition.PRESSED, RPM.rpm(0d));
    private final GasPosition gasThreshold;
    private final RPM rpmThreshold;

    KickdownPolicy(GasPosition gasThreshold, RPM rpmThreshold) {
        this.gasThreshold = gasThreshold;
        this.rpmThreshold = rpmThreshold;
    }

    boolean isApplicable(GasPosition gasPosition, RPM rpm) {
        return gasPosition.isLowerThan(gasThreshold) && rpm.isLowerThan(rpmThreshold);
    }

    Gear apply(GasPosition gasPosition, RPM currentRPM, Gear currentGear, GearRange gearRange) {
        if (gasPosition.isLowerThan(gasThreshold) && currentRPM.isLowerThan(rpmThreshold)) {
            return gearRange.previous(currentGear);
        }
        return currentGear;
    }
}

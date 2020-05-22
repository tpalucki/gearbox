class GearCalculator {

    private GearRange gearRange;

    GearCalculator(GearRange gearRange) {
        this.gearRange = gearRange;
    }

    Gear calculate(RPM currentRPM, Gear currentGear, GasPosition gasPosition, DriveMode driveMode) {
        RPMRange rpmRange = driveMode.optimalRPMRange();
        KickdownPolicy kickdownPolicy = driveMode.kickdownPolicy();

        if (kickdownPolicy.isApplicable(gasPosition, currentRPM)) {
            return kickdownPolicy.apply(gasPosition, currentRPM, currentGear, gearRange);
        } else if (currentRPM.isAbove(rpmRange)) {
            return gearRange.next(currentGear);
        } else if (currentRPM.isBelow(rpmRange)) {
            return gearRange.previous(currentGear);
        }
        return currentGear;
    }
}

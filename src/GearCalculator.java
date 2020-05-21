class GearCalculator {

    private GearRange gearRange;

    GearCalculator(GearRange gearRange) {
        this.gearRange = gearRange;
    }

    Gear calculate(RPM currentRPM, Gear currentGear, RPMRange rpmRange) {
        if (currentRPM.isAbove(rpmRange)) {
            return gearRange.next(currentGear);
        } else if (currentRPM.isBelow(rpmRange)) {
            return gearRange.previous(currentGear);
        }
        return currentGear;
    }
}

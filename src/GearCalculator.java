class GearCalculator {

    private GearRange gearRange;
    private RPMRange optimalRange;


    GearCalculator(GearRange gearRange, RPMRange optimalRPMRange) {
        this.gearRange = gearRange;
        this.optimalRange = optimalRPMRange;
    }

    Gear calculate(RPM currentRPM, Gear currentGear) {
        if (currentRPM.isAbove(optimalRange)) {
            return gearRange.next(currentGear);
        } else if (currentRPM.isBelow(optimalRange)) {
            return gearRange.previous(currentGear);
        }
        return currentGear;
    }
}

class GearRange {

    private final Gear minGear;
    private final Gear maxGear;

    GearRange(Gear min, Gear max) {
        this.minGear = min;
        this.maxGear = max;
    }


    Gear next(Gear gear) {
        if (gear.isLowerThan(maxGear)) {
            return new Gear(gear.asInt() + 1);
        } else {
            return maxGear;
        }
    }

    Gear previous(Gear gear) {
        if (gear.isGreaterThan(minGear)) {
            return new Gear(gear.asInt() - 1);
        } else {
            return minGear;
        }
    }
}

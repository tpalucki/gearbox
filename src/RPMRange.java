class RPMRange {

    private final RPM min;
    private final RPM max;

    RPMRange(RPM min, RPM max) {
        this.min = min;
        this.max = max;
    }

    boolean isBelow(RPM rpm) {
        return max.isLowerThan(rpm);
    }

    boolean isAbove(RPM rpm) {
        return min.isGreaterThan(rpm);
    }
}

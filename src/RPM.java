class RPM {
    private final double value;

    RPM(double value) {
        this.value = value;
    }

    static RPM rpm(double rpm) {
        return new RPM(rpm);
    }

    static RPM k(double rpm) {
        return RPM.rpm(rpm * 1000d);
    }

    boolean isAbove(RPMRange range) {
        return range.isBelow(this);
    }

    boolean isBelow(RPMRange range) {
        return range.isAbove(this);
    }

    boolean isLowerThan(RPM rpm) {
        return this.value < rpm.asDouble();
    }

    boolean isGreaterThan(RPM rpm) {
        return this.value > rpm.asDouble();
    }

    private double asDouble() {
        return this.value;
    }

}

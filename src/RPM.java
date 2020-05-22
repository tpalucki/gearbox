class RPM {
    private final double value;

    RPM(double value) {
        if (value < 0) {
            throw new IllegalArgumentException();
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RPM rpm = (RPM) o;

        return Double.compare(rpm.value, value) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }
}

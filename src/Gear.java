class Gear {
    private final int value;

    private static final int MIN_GEAR = 1;

    Gear(int gear) {
        if (gear < MIN_GEAR) {
            throw new IllegalArgumentException();
        }
        this.value = gear;
    }

    int asInt() {
        return this.value;
    }

    boolean isGreaterThan(Gear gear) {
        return this.value > gear.asInt();
    }

    boolean isLowerThan(Gear gear) {
        return this.value < gear.asInt();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gear gear = (Gear) o;
        return value == gear.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}

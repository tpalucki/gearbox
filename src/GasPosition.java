
class GasPosition {

    final static GasPosition PRESSED = GasPosition.of(0);
    final static GasPosition RELEASED = GasPosition.of(1);

    private final double pedalPosition;

    private GasPosition(double position) {
        pedalPosition = position;
    }

    static GasPosition of(double value) {
        return new GasPosition(value);
    }

    boolean isLowerThan(GasPosition position) {
        return this.pedalPosition < position.pedalPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GasPosition that = (GasPosition) o;

        return Double.compare(that.pedalPosition, pedalPosition) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(pedalPosition);
        return (int) (temp ^ (temp >>> 32));
    }
}

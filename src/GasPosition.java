
class GasPosition {

    final static private double min = 0d;
    final static private double max = 1d;
    final static GasPosition PRESSED = GasPosition.of(min);
    final static GasPosition RELEASED = GasPosition.of(max);

    private final double pedalPosition;

    private GasPosition(double position) {
        if (position < min || position > max) {
            throw new IllegalArgumentException();
        }
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


class PedalPosition {

    final static PedalPosition PRESSED = PedalPosition.of(0);
    final static PedalPosition RELEASED = PedalPosition.of(1);

    private final double pedalPosition;

    private PedalPosition(double position) {
        pedalPosition = position;
    }

    static PedalPosition of(double value) {
        return new PedalPosition(value);
    }

    boolean isPressedMoreThan(PedalPosition position) {
        return this.pedalPosition < position.pedalPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PedalPosition that = (PedalPosition) o;

        return Double.compare(that.pedalPosition, pedalPosition) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(pedalPosition);
        return (int) (temp ^ (temp >>> 32));
    }
}

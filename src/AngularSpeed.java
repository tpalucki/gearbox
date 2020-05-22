class AngularSpeed {
    private final double speed;

    AngularSpeed(double angularSpeed) {
        this.speed = angularSpeed;
    }

    boolean isZero() {
        return speed == 0;
    }
}

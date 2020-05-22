class ExternalSystemsFacade {

    private final ExternalSystems externalSystems;

    ExternalSystemsFacade(ExternalSystems externalSystems) {
        this.externalSystems = externalSystems;
    }

    RPM currentRPM() {
        return new RPM(externalSystems.getCurrentRpm());
    }

    AngularSpeed currentAngularSpeed() {
        return new AngularSpeed(externalSystems.getAngularSpeed());
    }

    boolean isTiltDown() {
        final Integer lightPositoin = externalSystems.getLights().getLightsPosition();
        return lightPositoin != null && lightPositoin >= 1 && lightPositoin <= 3;

    }

    PedalPosition gasPosition() {
        return PedalPosition.of(0.3d);
    }

    boolean isBreakPressed() {
        return false;
    }

    boolean isGlide() {
        return externalSystems.getAngularSpeed() != 0;
    }
}

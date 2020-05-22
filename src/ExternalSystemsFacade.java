class ExternalSystemsFacade {

    private final ExternalSystems externalSystems;

    ExternalSystemsFacade(ExternalSystems externalSystems) {
        this.externalSystems = externalSystems;
    }

    RPM currentRPM() {
        return new RPM(externalSystems.getCurrentRpm());
    }

    boolean isTiltDown() {
        final Integer lightPositoin = externalSystems.getLights().getLightsPosition();
        return lightPositoin != null && lightPositoin >= 1 && lightPositoin <= 3;

    }

    GasPosition gasPosition() {
        return GasPosition.of(0.3d);
    }

    boolean isBreakPressed() {
        return false;
    }

    boolean isGlide() {
        return externalSystems.getAngularSpeed() != 0;
    }

    boolean isTrailerConnected() {
        return false;
    }
}

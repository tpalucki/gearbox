import provided.ExternalSystems;

class ExternalSystemsFacade {

    private final ExternalSystems externalSystems;
    private final CarDataProvider carDataProvider;

    ExternalSystemsFacade(ExternalSystems externalSystems, CarDataProvider carDataProvider) {
        this.externalSystems = externalSystems;
        this.carDataProvider = carDataProvider;
    }

    RPM currentRPM() {
        return RPM.rpm(externalSystems.getCurrentRpm());
    }

    boolean isTiltDown() {
        final Integer lightPositoin = externalSystems.getLights().getLightsPosition();
        return lightPositoin != null && lightPositoin >= 1 && lightPositoin <= 3;
    }

    GasPosition gasPosition() {
        return GasPosition.of(0.3d);
    }

    boolean isBreakPressed() {
        return carDataProvider.isBreakPressed();
    }

    boolean isGlide() {
        return externalSystems.getAngularSpeed() != 0;
    }

    boolean isTrailerConnected() {
        return carDataProvider.isTrailerConnected();
    }
}

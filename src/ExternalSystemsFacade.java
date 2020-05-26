import provided.ExternalSystems;

class ExternalSystemsFacade {

    private final ExternalSystems externalSystems;
    private final CarDataProvider carDataProvider;
    private AggressiveMode rpmModifier = AggressiveMode.MODE_1;

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
        return GasPosition.of(carDataProvider.currentGasPosition());
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

    AggressiveMode updateRPMModifier(AggressiveMode aggressiveMode) {
        return rpmModifier = aggressiveMode;
    }

    AggressiveMode currentRPMModifier() {
        return rpmModifier;
    }
}

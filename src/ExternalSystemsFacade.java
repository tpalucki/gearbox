public class ExternalSystemsFacade {

    private final ExternalSystems externalSystems;

    ExternalSystemsFacade(ExternalSystems externalSystems) {
        this.externalSystems = externalSystems;
    }

    RPM currentRPM() {
        return new RPM(this.externalSystems.getCurrentRpm());
    }

    AngularSpeed currentAngularSpeed() {
        return new AngularSpeed(this.externalSystems.getAngularSpeed());
    }

    boolean isTiltDown() {
        final Integer lightPositoin = this.externalSystems.getLights().getLightsPosition();
        return lightPositoin != null && lightPositoin >= 1 && lightPositoin <= 3;

    }
}

class GearboxFacade {

    private final Gearbox gearbox;

    GearboxFacade(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    boolean isDrive() {
        return (Integer) this.gearbox.getState() == 1;
    }

    boolean isPark() {
        return (Integer) this.gearbox.getState() == 2;
    }

    boolean isReverse() {
        return (Integer) this.gearbox.getState() == 3;
    }

    boolean isNeutral() {
        return (Integer) this.gearbox.getState() == 4;
    }

    Gear currentGear() {
        return new Gear((Integer) this.gearbox.getCurrentGear());
    }

    void setGear(Gear gear) {
        this.gearbox.setCurrentGear(gear.asInt());
    }
}

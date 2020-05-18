class GearboxACL {

    private final Gearbox gearbox;

    GearboxACL(Gearbox gearbox) {
        this.gearbox = gearbox;
    }


    boolean drive() {
        return (Integer) this.gearbox.getState() == 1;
    }

    boolean park() {
        return (Integer) this.gearbox.getState() == 2;
    }

    boolean reverse() {
        return (Integer) this.gearbox.getState() == 3;
    }

    boolean neutral() {
        return (Integer) this.gearbox.getState() == 4;
    }

    Gear currentGear() {
        return new Gear((Integer) this.gearbox.getCurrentGear());
    }

    void setGear(Gear gear) {
        this.gearbox.setCurrentGear(gear.asInt());
    }
}

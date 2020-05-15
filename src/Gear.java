class Gear {
    private GearEnum value;

    Gear(GearEnum value) {
        this.value = value;
    }

    GearEnum value() {
        return this.value;
    }

    // TODO maybe implement as a list of gears - iteratin up and down of list would be easier

    public enum GearEnum {
        GEAR_PARK(-2),
        GEAR_REVERSE(-1),
        GEAR_NEUTRAL(0),
        GEAR_1(1),
        GEAR_2(2),
        GEAR_3(3),
        GEAR_4(4),
        GEAR_5(5),
        GEAR_6(6),
        GEAR_7(7),
        GEAR_8(8);

        private int value;

        GearEnum(int value) {
            this.value = value;
        }

//        GearEnum below(GearEnum gearEnum) {
//            gearEnum value
//        }
//
//        GearEnum above(GearEnum)
    }
}

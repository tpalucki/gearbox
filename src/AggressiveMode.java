/**
 * Instead of modifying RPM's for comparison in gear calculator, I realized we can achieve same effect of faster gear changing
 * by modying RPMs at the source of this data, so in the external systems. This is opposite side of the equation so
 * modifier has to be inverted. That is why instead of +30% we do -30% on the RPMs value;
 */
public enum AggressiveMode {
    MODE_1(1d),
    MODE_2(1 / 1.3d),
    MODE_3(1 / 1.3d);

    private final double rpmModifier;

    AggressiveMode(double rpmModifier) {
        this.rpmModifier = rpmModifier;
    }

    double asDouble() {
        return rpmModifier;
    }
}

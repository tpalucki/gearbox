enum DriveMode {
    ECO(new RPMRange(RPM.k(1), RPM.k(2)), KickdownPolicy.NO_KICKDOWN),
    COMFORT(new RPMRange(RPM.k(1), RPM.k(2.5)), new KickdownPolicy(GasPosition.of(0.5d), RPM.rpm(4500d))),
    SPORT(new RPMRange(RPM.rpm(1.5), RPM.k(5)), new SportKickdownPolicy(GasPosition.of(0.5d), RPM.k(5d), GasPosition.of(0.7d), RPM.k(5d)));

    private final RPMRange associatedRange;
    private final KickdownPolicy kickdownPolicy;

    DriveMode(RPMRange range, KickdownPolicy policy) {
        associatedRange = range;
        kickdownPolicy = policy;
    }

    RPMRange optimalRPMRange() {
        return associatedRange;
    }

    public KickdownPolicy kickdownPolicy() {
        return kickdownPolicy;
    }
}
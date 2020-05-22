import java.util.*;

class KickdownPolicy {

    static final KickdownPolicy NONE = new KickdownPolicy(List.of(PedalPosition.PRESSED));
    private final List<PedalPosition> gasThresholds;

    private KickdownPolicy(List<PedalPosition> thresholds) {
        gasThresholds = thresholds;

    }

    static KickdownPolicy fromThresholds(List<PedalPosition> thresholdsList) {
        return new KickdownPolicy(thresholdsList);
    }

    boolean isApplicable(PedalPosition gasPosition) {
        return gasPosition.isPressedMoreThan(gasThresholds.get(0));
    }

    Gear apply(PedalPosition position, Gear currentGear, GearRange gearRange) {
        Iterator<PedalPosition> iterator = gasThresholds.iterator();
        Gear gear = currentGear;
        while (iterator.hasNext()) {
            PedalPosition threshold = iterator.next();
            if (position.isPressedMoreThan(threshold)) {
                gear = gearRange.previous(gear);
            }
        }
        return gear;
    }
}

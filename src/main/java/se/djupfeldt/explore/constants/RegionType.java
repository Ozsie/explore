package se.djupfeldt.explore.constants;

/**
 * Created by vi on 2017-01-17.
 */
public enum RegionType {
    PLAINS("P", 10), HILLS("H",5), TUNDRA("T",3), DESERT("D",3), MARSH("M",4);

    String display;
    long modifier;

    RegionType(String display, long modifier) {
        this.display = display;
        this.modifier = modifier;
    }

    public String getDisplay() {
        return display;
    }

    public long getModifier() {
        return modifier;
    }

    @Override
    public String toString() {
        return display;
    }
}

package se.djupfeldt.explore.constants;

/**
 * Created by vi on 2017-01-17.
 */
public enum RegionFeature {
    NONE(""), ROCKY("."), URBAN("#"), FOREST("*"), MOUNTAINS("^");

    String display;

    RegionFeature(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    @Override
    public String toString() {
        return display;
    }
}

package se.djupfeldt.explore.dto;

import se.djupfeldt.explore.constants.RegionFeature;
import se.djupfeldt.explore.constants.RegionType;

import java.util.Set;

/**
 * Created by vi on 2017-01-17.
 */
public class Region {

    private Coordinate coordinates;
    private RegionType type;
    private Set<RegionFeature> features;

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public RegionType getType() {
        return type;
    }

    public void setType(RegionType type) {
        this.type = type;
    }

    public Set<RegionFeature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<RegionFeature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return coordinates.toString() + ", " + type + ", " + features.toString();
    }
}

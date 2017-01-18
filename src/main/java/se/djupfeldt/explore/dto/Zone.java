package se.djupfeldt.explore.dto;

import se.djupfeldt.explore.constants.ZoneFeatures;
import se.djupfeldt.explore.constants.ZoneTypes;

import java.util.Set;

/**
 * Created by vi on 2017-01-17.
 */
public class Zone {

    private Coordinate coordinates;
    private ZoneTypes type;
    private Set<ZoneFeatures> features;

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public ZoneTypes getType() {
        return type;
    }

    public void setType(ZoneTypes type) {
        this.type = type;
    }

    public Set<ZoneFeatures> getFeatures() {
        return features;
    }

    public void setFeatures(Set<ZoneFeatures> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return coordinates.toString() + ", " + type + ", " + features.toString();
    }
}

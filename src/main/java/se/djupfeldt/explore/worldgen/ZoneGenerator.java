package se.djupfeldt.explore.worldgen;

import se.djupfeldt.explore.constants.ZoneFeatures;
import se.djupfeldt.explore.constants.ZoneTypes;
import se.djupfeldt.explore.dto.Coordinate;
import se.djupfeldt.explore.dto.Zone;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by vi on 2017-01-17.
 */
@LocalBean
public class ZoneGenerator {

    @EJB
    ZoneStore zoneStore;

    public Zone getZone(long x, long y, long seed) {
        Zone zone = zoneStore.getZone(x, y);
        if (zone != null) {
            return zone;
        }
        zone = new Zone();
        Coordinate coordinates = getCoordinates(x, y);
        zone.setCoordinates(coordinates);

        ZoneTypes type = getZoneType(x, y, seed);
        zone.setType(type);

        Set<ZoneFeatures> features = getFeatures(x, y, seed);
        zone.setFeatures(features);

        return zone;
    }

    Coordinate getCoordinates(long x, long y) {
        Coordinate coordinates = new Coordinate();
        coordinates.setY(y);
        coordinates.setX(x);
        return coordinates;
    }

    ZoneTypes getZoneType(long x, long y, long seed) {
        long typeSeed = (x + y) * seed;
        long types = ZoneTypes.values().length;
        int id = (int)(typeSeed % types);
        return ZoneTypes.values()[id];
    }

    Set<ZoneFeatures> getFeatures(long x, long y, long seed) {
        Set<ZoneFeatures> featuresList = new HashSet<>();
        long xMod = x + 1;
        long yMod = y + 1;
        int numberOfFeatures = (int) Math.ceil(Math.sin((double) xMod/yMod/seed) * 3);
        System.out.println("FEATURES: " + numberOfFeatures);
        for (int i = 0; i < numberOfFeatures; i++) {
            long featureSeed = (xMod + yMod) * seed * (i + 1);

            long features = ZoneFeatures.values().length;

            int id = (int) (featureSeed % features);

            System.out.println("ID: " + id);
            ZoneFeatures feature = ZoneFeatures.values()[id];
            featuresList.add(feature);
        }
        return featuresList;
    }
}

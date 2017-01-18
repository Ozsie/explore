package se.djupfeldt.explore.worldgen;

import se.djupfeldt.explore.constants.RegionFeature;
import se.djupfeldt.explore.constants.RegionType;
import se.djupfeldt.explore.dto.Coordinate;
import se.djupfeldt.explore.dto.Region;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by vi on 2017-01-17.
 */
@LocalBean
public class RegionGenerator {

    @EJB
    RegionStore regionStore;

    public Region getRegion(long x, long y, long seed) {
        Region region = regionStore.getZone(x, y);
        if (region != null) {
            return region;
        }
        region = new Region();
        Coordinate coordinates = getCoordinates(x, y);
        region.setCoordinates(coordinates);
        if (x == 0 && y == 0) {

            RegionType type = getZoneType(x, y, seed);
            region.setType(type);

            Set<RegionFeature> features = new HashSet<>();
            features.add(RegionFeature.URBAN);
            region.setFeatures(features);

        } else {
            Set<Region> affectionRegions = new HashSet<>();
            for (int xMod = 1; x > -1; x--) {
                for (int yMod = 1; y > -1; y--) {
                    Region r = regionStore.getZone(x+xMod,y+yMod);
                    if (r != null) {
                        affectionRegions.add(r);
                    }
                }
            }
            RegionType type = getZoneType(x, y, seed, affectionRegions);
            region.setType(type);

            Set<RegionFeature> features = getFeatures(x, y, seed, affectionRegions);
            features.add(RegionFeature.URBAN);
            region.setFeatures(features);

        }
        return region;
    }

    Coordinate getCoordinates(long x, long y) {
        Coordinate coordinates = new Coordinate();
        coordinates.setY(y);
        coordinates.setX(x);
        return coordinates;
    }

    RegionType getZoneType(long x, long y, long seed) {
        long typeSeed = (x + y) * seed;
        Random rnd = new Random(typeSeed);
        long types = RegionType.values().length;
        int multiplier = (int) Math.max(typeSeed, (long)Integer.MAX_VALUE);
        if (multiplier < 0) {
            multiplier = 1;
        }
        int id = (int)(rnd.nextInt(multiplier) % types);
        int next = rnd.nextInt((int)RegionType.PLAINS.getModifier());
        long modifier = RegionType.values()[id].getModifier();
        if (next <= modifier) {
            return RegionType.values()[id];
        } else {
            next = rnd.nextInt((int) types);
            return RegionType.values()[next];
        }
    }

    RegionType getZoneType(long x, long y, long seed, Set<Region> affectionRegions) {
        HashMap<RegionType, Integer> typeCounts = new HashMap<>();
        for (Region r : affectionRegions) {
            Integer count = typeCounts.get(r.getType());
            if (count == null) {
                count = 1;
            } else {
                count++;
            }
            typeCounts.put(r.getType(), count);
        }
        for (RegionType type : typeCounts.keySet()) {
            Integer count = typeCounts.get(type);
            if (count > 6) {
                return type;
            }
        }
        return getZoneType(x, y, seed);
    }

    Set<RegionFeature> getFeatures(long x, long y, long seed, Set<Region> affectionRegions) {
        Set<RegionFeature> featuresList = new HashSet<>();
        long xMod = x + 1;
        long yMod = y + 1;
        int numberOfFeatures = (int) Math.ceil(Math.sin((double) xMod/yMod/seed) * 3);
        for (int i = 0; i < numberOfFeatures; i++) {
            long featureSeed = (xMod + yMod) * seed * (i + 1);

            long features = RegionFeature.values().length;

            int id = Math.abs((int) (featureSeed % features));
            id = Math.min(id, RegionFeature.values().length - 1);

            RegionFeature feature = RegionFeature.values()[id];
            featuresList.add(feature);
        }
        return featuresList;
    }
}

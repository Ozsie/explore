package se.djupfeldt.explore.worldgen;

import se.djupfeldt.explore.dto.Coordinate;
import se.djupfeldt.explore.dto.Region;

import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vi on 2017-01-17.
 */
@Singleton
public class RegionStore {

    private Map<Coordinate, Region> regions;

    public Map<Coordinate, Region> getRegions() {
        if (regions == null) {
            regions = new HashMap<>();
        }
        return regions;
    }

    public Region getZone(long x, long y) {
        Coordinate coordinate = new Coordinate();
        coordinate.setX(x);
        coordinate.setY(y);
        return getRegions().get(coordinate);
    }
}

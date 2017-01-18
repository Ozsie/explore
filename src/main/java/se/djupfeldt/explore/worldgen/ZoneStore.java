package se.djupfeldt.explore.worldgen;

import se.djupfeldt.explore.dto.Coordinate;
import se.djupfeldt.explore.dto.Zone;

import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vi on 2017-01-17.
 */
@Singleton
public class ZoneStore {

    private Map<Coordinate, Zone> zones;

    public Map<Coordinate, Zone> getZones() {
        if (zones == null) {
            zones = new HashMap<>();
        }
        return zones;
    }

    public Zone getZone(long x, long y) {
        Coordinate coordinate = new Coordinate();
        coordinate.setX(x);
        coordinate.setY(y);
        return getZones().get(coordinate);
    }
}

package se.djupfeldt.explore.worldgen;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.djupfeldt.explore.constants.RegionType;
import se.djupfeldt.explore.dto.Region;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

/**
 * Created by vi on 2017-01-17.
 */
public class TestZoneGenerator {

    RegionGenerator generator;

    @Before
    public void beforeTest() {
        generator = new RegionGenerator();
        generator.regionStore = mock(RegionStore.class);
    }

    @Test
    public void testGetZone() {
        when(generator.regionStore.getZone(0,0)).thenReturn(null);
        Region zone00 = generator.getRegion(0,0,4);
        System.out.println(zone00);
    }

    @Test
    public void testGetZones() {
        int rad = 5;
        for (int x = rad; x > -rad; x--) {
            for (int y = rad; y > -rad; y--) {
                Region zone = generator.getRegion(x, y,2141234236);
                System.out.print(zone.getType());
            }
            System.out.println("");
        }
    }

    @Test
    public void testGetZoneType() {
        try {
            RegionType type = generator.getZoneType(1,1,23);
            System.out.println(type.name());
        } catch (IndexOutOfBoundsException e) {
            Assert.fail();
        }
    }

    @Test
    public void testGetZoneTypeIdAlwaysExists() {
        Random rnd = new Random();
        try {
            for (int i = 0; i < 50; i++) {
                RegionType type = generator.getZoneType(rnd.nextInt(10000), rnd.nextInt(10000), rnd.nextInt(50000));
                System.out.println(type.name());
            }
        } catch (IndexOutOfBoundsException e) {
            Assert.fail();
        }
    }
}

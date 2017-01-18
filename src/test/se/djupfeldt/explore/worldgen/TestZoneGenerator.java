package se.djupfeldt.explore.worldgen;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import se.djupfeldt.explore.constants.ZoneTypes;
import se.djupfeldt.explore.dto.Zone;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;

/**
 * Created by vi on 2017-01-17.
 */
public class TestZoneGenerator {

    ZoneGenerator generator;

    @Before
    public void beforeTest() {
        generator = new ZoneGenerator();
        generator.zoneStore = mock(ZoneStore.class);
    }

    @Test
    public void testGetZone() {
        when(generator.zoneStore.getZone(0,0)).thenReturn(null);
        Zone zone00 = generator.getZone(0,0,4);
        System.out.println(zone00);
    }

    @Test
    public void testGetZones() {
        for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 50; y++) {
                Zone zone = generator.getZone(x, y,4);
                System.out.println(zone);
            }
        }
    }

    @Test
    public void testGetZoneType() {
        try {
            ZoneTypes type = generator.getZoneType(5,7,342351254);
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
                ZoneTypes type = generator.getZoneType(rnd.nextInt(10000), rnd.nextInt(10000), rnd.nextInt(50000));
                System.out.println(type.name());
            }
        } catch (IndexOutOfBoundsException e) {
            Assert.fail();
        }
    }
}

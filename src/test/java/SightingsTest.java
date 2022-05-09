import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SightingsTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private String name;

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Sightings testSightings = new Sightings(1,"Zone A","Monari");
        assertEquals(true, testSightings instanceof Sightings);
    }
    @Test
    public void getLocation_InstantiatesWithLocation_ZoneA() {
        Sightings testSightings = new Sightings(1,"Zone A","Monari");
        assertEquals("Zone A", testSightings.getLocation());
    }
    @Test
    public void getRangerName_InstantiatesWithName_Monari() {
        Sightings testSightings = new Sightings(1,"Zone A","Monari");
        assertEquals("Monari", testSightings.getRangerName());
    }
    @Test
    public void getAnimalId_InstantiatesWithAnimalId_1() {
        Sightings testSightings = new Sightings(1,"Zone A","Monari");
        assertEquals(1, testSightings.getAnimalId());
    }







}
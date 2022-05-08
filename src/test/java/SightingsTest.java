import org.junit.Rule;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SightingsTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();
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

//    @Test
//    public void all_returnsAllInstancesOfSightings_true() {
//        Sightings testSightings = new Sightings(20,"Zone B","Monari");
//        testSightings.save();
//        Sightings anotherSightings = new Sightings(11,"Near the River","Alexina");
//        anotherSightings.save();
//        assertEquals(true, Sightings.all().get(0).equals(testSightings));
//        assertEquals(true, Sightings.all().get(1).equals(anotherSightings));
//    }

//    @Test
//    public void getSightings_retrievesAllSightingsFromDatabase_SightingsList() {
//        Sightings testSightings = new Sightings(1,"Zone A","Monari");
//        testSightings.save();
//        Sightings firstSightings = new Sightings("Zone B", testSightings.getId());
//        firstSightings.save();
//        Sightings secondSightings = new Sightings("Zone A", testSightings.getId());
//        secondSightings.save();
//        Sightings[] sightings = new Sightings[] { firstSightings, secondSightings };
//        assertTrue(testSightings.getSightings().containsAll(Arrays.asList(sightings)));
//    }

//    @Test
//    public void save_assignsIdToObject() {
//        Sightings testSightings = new Sightings(7,"Zone B","Gertrude");
//        testSightings.save();
//        Sightings savedSightings = Sightings.all().get(0);
//        assertEquals(testSightings.getId(), savedSightings.getId());
//    }
//////check
    @Test
    public void save_insertsObjectIntoDatabase() {
        Sightings testSightings = new Sightings(9,"Zone A","Monari");
        testSightings.save();
        assertFalse(Sightings.all().get(0).equals(testSightings));
    }

    @Test
    public void save_recordsTimeOfCreationInDatabase() {
        Sightings testSightings = new Sightings(3,"Near the river","Douglas");
        testSightings.save();
        Timestamp savedAnimalSighting = Sightings.find(testSightings.getId()).getTime();
        Timestamp rightNow = new Timestamp(new Date().getTime());
        assertEquals(rightNow.getDay(), savedAnimalSighting.getDay());
    }

    @Test
    public void delete_deletesSightings_true() {
        Sightings testSightings = new Sightings(2,"Zone B","Monari");
        testSightings.save();
        testSightings.delete();
        assertEquals(null,Sightings.find(testSightings.getId()));
    }

}
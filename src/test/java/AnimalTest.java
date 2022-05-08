import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal("Zebra", "impervious");
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    public void getName_animalInstantiatesWithName_Zebra() {
        Animal testAnimal = new Animal("Zebra","impervious");
        assertEquals("Zebra", testAnimal.getName());
    }

    @Test
    public void getStatus_animalInstantiatesWithStatus_Zebra() {
        Animal testAnimal = new Animal("Zebra","impervious");
        assertEquals("impervious", testAnimal.getType());
    }
    //failed

    @Test
    public void save_insertsObjectIntoDatabase_Animal() {
        Animal testAnimal = new Animal("Zebra","impervious");
        testAnimal.save();
        assertTrue(Animal.all().get(0).equals(testAnimal));
    }



    //failed
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal firstAnimal = new Animal("Lion","impervious");
        firstAnimal.save();
        Animal secondAnimal = new Animal("Monkey","impervious");;
        secondAnimal.save();
        Assertions.assertEquals(true,Animal.all().equals(firstAnimal));
        Assertions.assertEquals(true, Animal.all().equals(secondAnimal));
    }


    @Test
    public void save_assignsIdToObject() {
        Animal testAnimal = new Animal("Zebra","impervious");
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(savedAnimal.getId(), savedAnimal.getId());
    }

//    @Test
//    public void find_returnsAnimalWithSameId_secondAnimal() {
//        Animal firstAnimal = new Animal("Lion","impervious");
//        firstAnimal.save();
//        Animal secondAnimal = new Animal("Monkey","impervious");
//        secondAnimal.save();
//        assertEquals(Animal.find(secondAnimal.getId()), secondAnimal);
//    }

    @Test
    public void delete_deletesAnimal_true() {
        Animal testAnimal = new Animal("Zebra","impervious");
        testAnimal.save();
        testAnimal.delete();
        assertEquals(null,Animal.find(testAnimal.getId()));
    }





}
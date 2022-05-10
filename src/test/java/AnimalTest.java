import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;



class AnimalTest {
    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private String name;

    @Test
    public void animal_instantiatesCorrectly_true() {
        Animal testAnimal = new Animal("Zebra", "Not endangered");
        assertEquals(true, testAnimal instanceof Animal);
    }

    @Test
    public void getName_animalInstantiatesWithName_Zebra() {
        Animal testAnimal = new Animal("Zebra","Not endangered");
        assertEquals("Zebra", testAnimal.getName());
    }

    @Test
    public void getStatus_animalInstantiatesWithStatus_Zebra() {
        Animal testAnimal = new Animal("Zebra","Not endangered");
        assertEquals("Not endangered", testAnimal.getType());
    }


    @Test
    public void getType_animalInstantiatesWithType_Type() {
        Animal testAnimal = new Animal("Zebra","Not endangered");
        assertEquals("Not endangered", testAnimal.getType());
    }


    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        Animal firstAnimal = new Animal("Lion","Not endangered");
        firstAnimal.save();
        Animal secondAnimal = new Animal("Monkey","Not endangered");;
        secondAnimal.save();
        Assertions.assertTrue(true, String.valueOf(Animal.all().equals(firstAnimal)));
        Assertions.assertTrue(true, String.valueOf(Animal.all().equals(secondAnimal)));
    }


    @Test
    public void save_assignsIdToObject() {
        Animal testAnimal = new Animal("Zebra","Not endangered");
        testAnimal.save();
        Animal savedAnimal = Animal.all().get(0);
        assertEquals(savedAnimal.getId(), savedAnimal.getId());
    }



}
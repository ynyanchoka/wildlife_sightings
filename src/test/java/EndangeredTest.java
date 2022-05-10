
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class EndangeredTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();
    @Rule
    public final ExpectedException exception = ExpectedException.none();


    @Test
    public void endangeredAnimal_instantiatesCorrectly_true() {
        Endangered testEndangered = new Endangered("White rhino","ill","old", "endangered");
        assertEquals(true, testEndangered instanceof Endangered);
    }

    @Test
    public void getName_personInstantiatesWithName_Rhino() {
        Endangered testEndangered = new Endangered("White rhino","ill","adult","endangered");
        assertEquals("White rhino", testEndangered.getName());
    }
    @Test
    public void all_returnsAllInstancesOfEndangeredAnimals_true() {
        Endangered firstEndangered = new Endangered(" White Rhino", "ill","adult","endangered");
        firstEndangered.save();
        Endangered secondEndangered = new Endangered("Leopard", "healthy","young","endangered");
        secondEndangered.save();
        assertTrue(true, String.valueOf(Endangered.all().get(0).equals(firstEndangered)));
        assertTrue(true, String.valueOf(Endangered.all().get(1).equals(secondEndangered)));
    }




    @Test
    public void animal_instantiatesWithHealthIll(){
        Endangered testEndangered = new Endangered("White rhino","ill","adult","endangered");
        assertEquals(testEndangered.getHealth(), (Endangered.SICK ));
    }
    @Test
    public void animal_instantiatesWithHealthHealthy(){
        Endangered testEndangered = new Endangered("rhino","healthy","adult","endangered");
        assertEquals(testEndangered.getHealth(), (Endangered.WELLNESS ));
    }
    @Test
    public void animal_instantiatesWithHealthOkay(){
        Endangered testEndangered = new Endangered("rhino","okay","adult","endangered");
        assertEquals(testEndangered.getHealth(), (Endangered.FINE ));
    }

    @Test
    public void animal_instantiatesWithAgeAdult(){
        Endangered testEndangered = new Endangered("Zebra","ill","adult","endangered");
        assertEquals(testEndangered.getAge(), (Endangered.ADULT ));
    }
    @Test
    public void animal_instantiatesWithAgeYoung(){
        Endangered testEndangered = new Endangered("White rhino","ill","young","endangered");
        assertEquals(testEndangered.getAge(), (Endangered.YOUNG ));
    }
    @Test
    public void animal_instantiatesWithAgeNewborn(){
        Endangered testEndangered = new Endangered("White rhino","ill","newborn","endangered");
        assertEquals(testEndangered.getAge(), (Endangered.INFANT ));
    }










}
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EndangeredTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void endangeredAnimal_instantiatesCorrectly_true() {
        Endangered testEndangered = new Endangered("White rhino","ill","old");
        assertEquals(true, testEndangered instanceof Endangered);
    }

    @Test
    public void getName_personInstantiatesWithName_Rhino() {
        Endangered testEndangered = new Endangered("White rhino","ill","old");
        assertEquals("White rhino", testEndangered.getName());
    }

//    @Test
//    public void save_insertsObjectIntoDatabase_Endangered() {
//        Endangered testEndangered = new Endangered("White rhino","ill","old");
//        testEndangered.save();
//        assertTrue(Endangered.all().get(0).equals(testEndangered));
//    }
//    @Test
//    public void all_returnsAllInstancesOfEndangeredAnimals_true() {
//        Endangered firstEndangered = new Endangered(" White Rhino", "ill","old");
//        firstEndangered.save();
//        Endangered secondEndangered = new Endangered("Leopard", "healthy","young");
//        secondEndangered.save();
//        assertEquals(true, Endangered.all().get(0).equals(firstEndangered));
//        assertEquals(true, Endangered.all().get(1).equals(secondEndangered));
//    }
//        @Test
//        public void save_assignsIdToObject() {
//            Endangered testEndangered = new Endangered("White rhino","ill","old");
//            testEndangered.save();
//            Endangered savedEndangered = Endangered.all().get(0);
//            assertEquals(testEndangered.getId(), savedEndangered.getId());
//        }


}
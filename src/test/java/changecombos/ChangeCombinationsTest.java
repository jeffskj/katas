package changecombos;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

import changecombos.ChangeCombinations;
import changecombos.ChangeCombinations.Change;

public class ChangeCombinationsTest {

    @Test
    public void testGetAllChangeCombinations() {
        testForValue(3, 1);
        testForValue(8, 2);
        testForValue(10, 4);
        testForValue(15, 6);
        testForValue(25, 13);
        testForValue(50, 50);
        testForValue(250, 5126);
        System.out.println(ChangeCombinations.getAllChangeCombinations(1000).size());
        for (int i = 0; i < 100; i++) {
//            System.out.println(ChangeCombinations.getAllChangeCombinations(i*5).size());
        }
    }

    void testForValue(int amount, int expectedCombos) {
        Set<Change> combos = ChangeCombinations.getAllChangeCombinations(amount);
        print(combos);
        assertEquals(expectedCombos, combos.size());
        System.out.println("--------------------------------------------------");
    }
    
    void print(Set<Change> combos) {
        for (Change c : combos) {
            System.out.println(c);
        }
    }
}

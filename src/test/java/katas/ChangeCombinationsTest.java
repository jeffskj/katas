package katas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import katas.ChangeCombinations.Change;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class ChangeCombinationsTest {

    @Test
    public void testGetUniqueCombinations() {
        testForAmount(0, 0);
        testForAmount(5, 2);
        testForAmount(8, 2);
        testForAmount(10, 4);
        testForAmount(15, 6);
        testForAmount(25, 13);
        testForAmount(50, 50);
        
        testForAmount(100, 292);
        testForAmount(200, 2435);
        testForAmount(1000, 801438);
        testForAmount(2000, 801438);
    }
    
    private void testForAmount(int amount, int expectedSize) {
        Set<Change> combinations = ChangeCombinations.getUniqueCombinations(amount);
        for (Change change : combinations) {
            if (expectedSize < 20) {
                System.out.println(change);
            }
            assertEquals(amount, change.getTotalValue());
        }
        assertNotNull(combinations);
        assertEquals(expectedSize, combinations.size());
        System.out.println(StringUtils.repeat('-', 50));
    }
    
    @Test
    public void testUniqueCombosSkipNums() {
        Set<Change> combinations = ChangeCombinations.getUniqueCombinations(15);
        System.out.println(combinations);
        assertNotNull(combinations);
        assertEquals(6, combinations.size());
    }

}


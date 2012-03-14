package changecombos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class ChangeCombinations {

    public static Set<Change> getAllChangeCombinations(int amount) {
        Set<Change> combos = new HashSet<ChangeCombinations.Change>();
        getChangeCombinations(amount, new Change(), Denomination.FIFTY_CENT, combos);
        return combos;
    }
    
    private static void getChangeCombinations(int amount, Change current, Denomination denom, Set<Change> combos) {
        if (denom == Denomination.PENNY) {
            current.set(Denomination.PENNY, amount);
            combos.add(current);
            return;
        }
        
        for (int i = amount / denom.getValue(); i >= 0; i--) {
            Change c = current.copy();
            c.set(denom, i);
            getChangeCombinations(amount-i*denom.getValue(), c, Denomination.values()[denom.ordinal() + 1], combos);
        }
    }
    
    enum Denomination { 
        FIFTY_CENT(50), QUARTER(25), DIME(10), NICKEL(5), PENNY(1);
        
        private int value;
        
        private Denomination(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }
    
    static class Change {
        private int[] values = new int[Denomination.values().length];
        
        void set(Denomination d, int v) {
            values[d.ordinal()] = v;
        }
        
        int get(Denomination d) {
            return values[d.ordinal()];
        }
        
        Change copy() {
            Change copy = new Change();
            System.arraycopy(this.values, 0, copy.values, 0, values.length);
            return copy;
        }
        
        @Override
        public String toString() {
//            String out = "[";
//            for (Denomination d : Denomination.values()) {
//                out += d.name() + ":" + get(d) + ",";
//            }
//            return out + "]";
            return Arrays.toString(values);
        }
    }
}

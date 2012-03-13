package katas;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ChangeCombinations {
    
    public enum Denomination { 
        FIFTY_CENT(50), QUARTER(25), DIME(10), NICKEL(5), PENNY(1);
        
        private int value;
        
        private Denomination(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
        
        public int times(int num) {
            return value * num;
        }
    }
    
    public static Set<Change> getUniqueCombinations(int amount) {
        HashSet<ChangeCombinations.Change> combinations = new HashSet<ChangeCombinations.Change>();
        if (amount != 0) {
            getUniqueCombinations(new Change(), amount, Denomination.values(), combinations);
        }
        return combinations;
    }
    
    private static void getUniqueCombinations(Change current, int amount, Denomination[] remaining, Set<Change> combinations) {
        if (remaining.length == 1) {
            current.set(remaining[0], amount);
            combinations.add(current);
            return; 
        }
        
        Denomination startDenom = remaining[0]; 
        
        for (int i = amount / startDenom.getValue(); i >= 0; i--) {
            Change change = current.copy();
            change.set(startDenom, i);
            int remainder = amount - (startDenom.getValue() * i);
            getUniqueCombinations(change, remainder, ArrayUtils.subarray(remaining, 1, remaining.length), combinations);
        }
    }
    
    public static class Change {
        
        private int[] values = new int [Denomination.values().length];
    
        public void set(Denomination denomination, int amount) {
            values[denomination.ordinal()] = amount;
        }
        
        public int get(Denomination denomination) {
            return values[denomination.ordinal()];
        }
        
        public boolean hasValue() {
            for (int val : values) {
                if (val != 0) {
                    return true;
                }
            }
            
            return false;
        }
        
        public int getTotalValue() {
            int total = 0;
            for (int i = 0; i < values.length; i++) {
                total += Denomination.values()[i].times(values[i]);
            }
            return total;
        }
        
        @Override
        public boolean equals(Object obj) {
            return EqualsBuilder.reflectionEquals(obj, obj);
        }
        
        @Override
        public int hashCode() {
            return new HashCodeBuilder().append(values).toHashCode();
        }

        protected Change copy() {
            Change other = new Change();
            System.arraycopy(values, 0, other.values, 0, values.length);
            return other;
        }
        
        @Override
        public String toString() {
            return Arrays.toString(values) + " = " + getTotalValue();
        }
    }
}

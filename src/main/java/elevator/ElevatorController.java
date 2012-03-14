package elevator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ElevatorController {
    private List<Elevator> elevators = Lists.newArrayList();
    
    public void requestStop(final int floor) {
        List<Elevator> available = Lists.newArrayList(Iterables.filter(elevators, new Predicate<Elevator>() {
            @Override
            public boolean apply(Elevator input) {
                return input.isFloorReachable(floor);
            }}));
        
        Collections.sort(available, new Comparator<Elevator>() {
            @Override
            public int compare(Elevator lhs, Elevator rhs) {
                return 0;
            }});
    }
}

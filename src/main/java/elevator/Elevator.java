package elevator;

import java.util.LinkedList;
import java.util.List;

public class Elevator {
    private Direction direction;
    private int topFloor;
    private int bottomFloor;
    private List<Integer> stopQueue = new LinkedList<Integer>();
    private int currentFloor;
    
    public Elevator(int topFloor, int bottomFloor) {
        this.topFloor = topFloor;
        this.bottomFloor = bottomFloor;
        currentFloor = bottomFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getTopFloor() {
        return topFloor;
    }

    public int getBottomFloor() {
        return bottomFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    
    public void addStop(int floor) {
        stopQueue.add(floor);
    }

    public boolean isFloorReachable(int floor) {
        return floor <= topFloor && floor >= bottomFloor;
    }
}

package Model.Factories;

import Controller.Directions;
import Model.Pit;
import Model.Room;
import Model.Utility;

import java.util.ArrayList;

public class RoomFactory {
    private static final int CONTAIN_LOW = 0;
    private static final int CONTAIN_HIGH = 4;

    private final Utility myUtility;
    private final MonsterFactory myMonsterFactory;
    private final ItemFactory myItemFactory;


    public RoomFactory() {
        myUtility = new Utility();
        myMonsterFactory = new MonsterFactory();
        myItemFactory = new ItemFactory();
    }

    /**
     * Creates a room in a random direction of the target room
     *
     * @param theTargetRoom The room to generate a random room on
     * @return The newly generated room
     */
    public Room makeRoomInRandomDirection(final Room theTargetRoom) {
        int directionNum = myUtility.getRandomInRange(0, 3);

        if(!theTargetRoom.hasNorth() && !theTargetRoom.hasSouth() && !theTargetRoom.hasEast() && !theTargetRoom.hasWest()) {
            return makeRoomInDirection(theTargetRoom, Directions.getDirection(directionNum));
        } else if(!theTargetRoom.hasMaxDoors()){
            //generate a new random number until we find a direction without a door, this is really dumb
            while(theTargetRoom.getDoors()[directionNum]) {
                directionNum = myUtility.getRandomInRange(0, 3);
            }
            return makeRoomInDirection(theTargetRoom, Directions.getDirection(directionNum));
        } else { //the room has the max amount of doors, so we can't generate a new room
            throw myUtility.getNewIllegal("The room" + theTargetRoom + " has the maximum amount of doors");
        }
    }

    public Room makeRoomInDirection(final Room theTargetRoom, final Directions.Direction theDirection) {
        // 0 = North, 1 = South, 2 = East, 3 = West

        Room newRoom = makeRandomRoom();

        switch (theDirection) {
            case NORTH -> makeNorthRoom(theTargetRoom, newRoom);
            case SOUTH -> makeSouthRoom(theTargetRoom, newRoom);
            case EAST -> makeEastRoom(theTargetRoom, newRoom);
            case WEST -> makeWestRoom(theTargetRoom, newRoom);
        }

        return newRoom;
    }

    public Room makeRandomRoom() {
        // 1 in 5 chance of having a random monster, a random item, a pit, nothing,
        // or a random monster and a random item
        ArrayList<Object> contents = new ArrayList<>();

        switch(myUtility.getRandomInRange(CONTAIN_LOW, CONTAIN_HIGH)) {
        default:
            contents.add(myMonsterFactory.makeRandomMonsterBug());
        case 1:
            contents.add(myItemFactory.makeRandomItem());
        case 2:
            contents.add(new Pit());
        case 4:
            contents.add(myMonsterFactory.makeRandomMonsterBug());
            contents.add(myItemFactory.makeRandomItem());
        }
        return new Room(contents);
    }


    public void makeNorthRoom(final Room theTarget, final Room theNorth) {
        theTarget.setNorth(theNorth);
        theNorth.setSouth(theTarget);
    }

    public void makeSouthRoom(final Room theTarget, final Room theSouth) {
        theTarget.setSouth(theSouth);
        theSouth.setNorth(theTarget);
    }

    public void makeEastRoom(final Room theTarget, final Room theEast) {
        theTarget.setEast(theEast);
        theEast.setWest(theTarget);
    }

    public void makeWestRoom(final Room theTarget, final Room theWest) {
        theTarget.setWest(theWest);
        theWest.setEast(theTarget);
    }
}

import java.util.*;

public class Hotel {
    private String name;
    private int costPerNight;
    private int stars;
    private boolean[] meals;
    private boolean pool;

    private String type = "basic";
    protected Map<Integer, Room> build = null;
    private int totalRooms;
    private boolean couplesOnly = false;


    public static final int breakfast = 0;
    public static final int lunch = 1;
    public static final int dinner = 2;

    public Hotel(String name, int costPerNight, int stars, boolean pool, boolean[] meals) {
        this.name = name;
        this.costPerNight = costPerNight;
        this.stars = stars;
        this.pool = pool;

        this.meals = new boolean[3];
        for (int i = 0; i < 3; i++) {
            this.meals[i] = meals[i];
        }
    }


    /**
     * The function creature the build of the hotel(Map<Integer,Room>)
     *
     * @param roomsStruct - array that represents the structure of the rooms in the hotel
     *                    The indexes represent the num of beds in any room and the value is the count of rooms with this num
     *                    For example:  if roomStruct[2] = 50, so there are 50 rooms in hotel with 3 beds(index+1)
     */
    public void initializeHotel(int[] roomsStruct, boolean balcony) {
        //update the map
        this.build = new HashMap<>();
        int index = 1;
        for (int i = 1; i < roomsStruct.length; i++) {
            for (int j = 0; j < roomsStruct[i]; j++) {
                Room room = new Room(index, i + 1, balcony); //creature a room
                build.put(index, room);
                index++;
            }
        }
        this.totalRooms = index - 1;            //update totalRooms
        if (this.totalRooms == roomsStruct[1])
            this.couplesOnly = true;          //update couplesOnly
    }

    public String getName() {
        return name;
    }

    public int getCostPerNight() {
        return costPerNight;
    }

    public int getStars() {
        return stars;
    }

    public boolean[] getMeals() {
        return meals;
    }

    public boolean hasPool() {
        return pool;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<Integer, Room> getBuild() {
        return this.build;
    }

    public int getTotalRooms() {
        return this.totalRooms;
    }

    public void setTotalRooms(int total) {
        this.totalRooms = total;
    }

    public boolean isCouplesOnly() {
        return couplesOnly;
    }

    public void setCouplesOnly(boolean ans) {
        this.couplesOnly = ans;
    }


    /**
     * @return iterator that points about each set(room+numberRoom) in the map
     */
    public Iterator<Map.Entry<Integer, Room>> getRoomIterator() {
        return build.entrySet().iterator();
    }

    /**
     * Get two dates and count of guests.
     * Go over all rooms with iterator.
     * Search for rooms that empty along all nights between these dates and contain count of beds same to guests.
     *
     * @return true if found any room, else false.
     */
    public boolean hasVacantRoom(int guests, myDate beg, myDate end) {
        Iterator<Map.Entry<Integer, Room>> iterator = getRoomIterator(); //iterator that points about each set(room+numberRoom) in the map
        while (iterator.hasNext()) {                                               //while the iterator points on sets in the map
            Map.Entry<Integer, Room> entry = iterator.next();                      //entry is the current set that the iterator points him
            if (entry.getValue().getBeds() == guests && entry.getValue().isEmpty(beg, end))                                //the value of entry(actually the rooms)
                return true;
        }
        return false;
    }

    /**
     * @param roomsRequest - array at size the count of rooms the user want. Each value in array rpresents the count of guests in every room.
     * @param beg          - The start date.
     * @param end          - The end date.
     * @return - True if exist enough vacant rooms for all rooms the user want at these dates. Else return false.
     */
    public boolean hasAvailableRooms(myDate beg, myDate end, int[] roomsRequest) {
        for (int i = 0; i < roomsRequest.length; i++) {
            if (!hasVacantRoom(roomsRequest[i], beg, end))
                return false;
        }
        return true;
    }


    /**
     * get number of room and range of dates and make the room to be occupied between these dates
     */
    public void addInvitation(int roomNumber, myDate beg, myDate end) {
        if (build.get(roomNumber).isEmpty(beg, end) == false)
            throw new IllegalArgumentException("The room is occupied!");
        Room r = this.build.get(roomNumber);
        r.addDates(beg, end);
    }


    /**
     * get number of room and range of dates and make the room to be vacant between these dates
     */
    public void cancelInvitation(int roomNumber, myDate beg, myDate end) {
        if (build.get(roomNumber).isEmpty(beg, end) == true)
            throw new IllegalArgumentException("The room is empty!");
        Room r = this.build.get(roomNumber);
        r.deleteDates(beg, end);
    }


    /**
     * @param guests - the request count of guests
     * @param beg    - start date
     * @param end    - end date
     * @return the number of room who empty at these dates and also can contain all guests
     * If not found return 0 (Not exist room with number 0)
     */
    public int getVacantRoom(int guests, myDate beg, myDate end) {
        int vacantRoomNumber = 0;
        Iterator<Map.Entry<Integer, Room>> iterator = getRoomIterator(); //iterator that points about each set(room+numberRoom) in the map
        while (iterator.hasNext()) {                                               //while the iterator points on sets in the map
            Map.Entry<Integer, Room> entry = iterator.next();                      //entry is the current set that the iterator points him
            if (entry.getValue().getBeds() == guests && entry.getValue().isEmpty(beg, end))                                //the value of entry(actually the rooms)
                vacantRoomNumber = entry.getKey();
        }
        return vacantRoomNumber;
    }

    /**
     * important note: the function call the function iff there are exist empty rooms for the guests that found before (after primary filter).
     *
     * @param beg          - start date
     * @param end          - end date
     * @param roomsRequest - size of array is display cont of rooms and each value display count of guests in each room
     *                     The function search empty room with request size and if found add the invitation to system at suitable dates
     */
    public void inviteRoom(myDate beg, myDate end, int[] roomsRequest) {
        for (int i = 0; i < roomsRequest.length; i++) {
            int roomNumber = getVacantRoom(roomsRequest[i], beg, end);
            if (roomNumber == 0)
                throw new IllegalArgumentException("Not available rooms were found!");
            addInvitation(roomNumber, beg, end);
        }
    }

    //get number of rooms and number of nights and return the cost of holiday in th hotel with these parameters
    public int vacationCost(int rooms, int nights) {
        return nights * rooms * costPerNight;
    }


}//end class

import java.util.HashMap;

public class LuxuryHotel extends Hotel {
    private boolean spa;
    private boolean restaurant;

    public LuxuryHotel(String name, int costPerNight, int stars, boolean pool, boolean[] meals, boolean spa, boolean restaurant) {
        super(name, costPerNight, stars, pool, meals);
        this.spa = spa;
        this.restaurant = restaurant;
        super.setType("Luxury");
    }
    @Override
    public void initializeHotel(int[] roomsStruct, boolean balcony) {
        this.build = new HashMap<>();
        int index = 1;
        for (int i = 1; i < roomsStruct.length; i++) {
            for (int j = 0; j < roomsStruct[i]; j++) {
                Premium room = new Premium(index, i+1, true, true); //creature a premium room
                build.put(index, room);
                index++;
            }
        }
        super.setTotalRooms(index-1);

        if(super.getTotalRooms() == roomsStruct[1])
            super.setCouplesOnly(true);
    }

    public boolean hasRestaurant() {
        return restaurant;
    }

    public boolean hasSpa() {
        return spa;
    }


}//end class

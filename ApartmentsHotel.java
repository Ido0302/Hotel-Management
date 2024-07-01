/**
 * class represents apartments hotel
 */

import java.util.HashMap;

public class ApartmentsHotel extends Hotel {
    private boolean jym;

    public ApartmentsHotel(String name, int costPerNight, int stars, boolean pool, boolean[] meals, boolean jym) {
        super(name, costPerNight, stars, pool, meals);
        this.jym = jym;
        super.setType("apartments");
    }


    @Override
    public void initializeHotel(int[] roomsStruct, boolean balcony) {
        this.build = new HashMap<>();
        int index = 1;
        for (int i = 1; i < roomsStruct.length; i++) {
            for (int j = 0; j < roomsStruct[i]; j++) {
                Apartment room = new Apartment(index, i+1, balcony, true, true); //creature an apartment
                build.put(index, room);
                index++;
            }
        }
        super.setTotalRooms(index-1);
        if(super.getTotalRooms() == roomsStruct[1])
            super.setCouplesOnly(true);
    }

    public boolean hasJym() {
        return jym;
    }



}//end class

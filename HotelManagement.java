import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class HotelManagement {
    private static HotelManagement instance;
    private ArrayList<Hotel> hotels;
    private Filter filter;
    private SearchBySort sortType;

    /**
     * private constructor to assume that the instance created just one time
     */
    private HotelManagement() {
        this.hotels = new ArrayList<>();
        creatureHotels();
        initializeHotels();
        filter = new Filter();
    }

    /**
     * The function call to constructor if and only if it's not create before
     * The function is static in order to allow use the function without instance
     */
    public static HotelManagement getInstance() {
        if (instance == null)
            instance = new HotelManagement();
        return instance;
    }

    /**
     * The function creature 10 hotels from difference shapes and with unique properties.
     */
    private void creatureHotels() {
        boolean[] meals = {false, false, false}; //represents if the hotel has/hasn't breakfast/lunch/dinner

        Hotel h = new Hotel("Marine", 300, 1, false, meals);
        hotels.add(h);
        h = new ApartmentsHotel("Bricks", 850, 2, false, meals, false);
        hotels.add(h);
        h = new ApartmentsHotel("Pi", 1300, 4, false, meals, true);
        hotels.add(h);

        meals[Hotel.breakfast] = true;

        h = new Hotel("Coconut", 500, 2, false, meals);
        hotels.add(h);
        h = new Hotel("DaysInn", 750, 3, true, meals);
        hotels.add(h);

        meals[Hotel.dinner] = true;
        h = new Hotel("Phuket Hotel", 1200, 4, true, meals);
        hotels.add(h);
        h = new LuxuryHotel("Sunset", 1500, 5, true, meals, false, true);
        hotels.add(h);

        meals[Hotel.lunch] = true;
        h = new Hotel("PP Princess", 1000, 4, true, meals);
        hotels.add(h);
        h = new LuxuryHotel("KC", 1700, 5, true, meals, true, false);
        hotels.add(h);
        h = new LuxuryHotel("Amari", 2000, 5, true, meals, true, true);
        hotels.add(h);
    }

    /**
     * The function initialize the map of rooms in each hotel according array that represents the rooms with different count of beds in each room and if room has balcony
     */
    private void initializeHotels() {
        int[] roomsStruct = new int[5]; //the first value represents if the room has balcony, the others represents the count of rooms with i+1 beds - maximum 5 beds in room
        roomsStruct[0] = 0;

        //hotel and isn't just for couples
        roomsStruct[1] = 10;
        roomsStruct[2] = 15;
        roomsStruct[3] = 15;
        roomsStruct[4] = 10;
        hotels.get(0).initializeHotel(roomsStruct, false);
        hotels.get(4).initializeHotel(roomsStruct, true);
        hotels.get(7).initializeHotel(roomsStruct, true);

        //apartments hotel
        roomsStruct[2] = 10;
        roomsStruct[4] = 15;
        hotels.get(1).initializeHotel(roomsStruct, false);
        hotels.get(2).initializeHotel(roomsStruct, true);

        //luxury and isn't just for couples
        roomsStruct[1] = 20;
        roomsStruct[2] = 30;
        roomsStruct[3] = 30;
        roomsStruct[4] = 20;
        hotels.get(6).initializeHotel(roomsStruct, true);
        hotels.get(9).initializeHotel(roomsStruct, true);

        //hotels just for couples
        roomsStruct[1] = 50;
        roomsStruct[2] = 0;
        roomsStruct[3] = 0;
        roomsStruct[4] = 0;
        hotels.get(3).initializeHotel(roomsStruct, true);
        hotels.get(5).initializeHotel(roomsStruct, true);

        //luxury just for couples
        roomsStruct[1] = 100;
        hotels.get(8).initializeHotel(roomsStruct, true);

    }

    public Iterator<Hotel> getHotelsIterator() {
        return this.hotels.iterator();
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public Hotel getHotelByName(String name) {
        Iterator<Hotel> iterator = this.getHotelsIterator();
        while (iterator.hasNext()) {
            Hotel hotel = iterator.next();
            if (hotel.getName().equalsIgnoreCase(name))
                return hotel;
        }
        return null;
    }

    public Filter getFilter() {
        return filter;
    }

    public ArrayList<Hotel> sort(ArrayList<Hotel> hotelsList) {
        return this.sortType.sort(hotelsList);
    }

    public void setSort(SearchBySort sortType) {
        this.sortType = sortType;
    }

    /**
     * The function get "key of filter" from the user and call to search function according user's choice
     *
     * @return array list of the required hotels according user's choice
     */
    public ArrayList<Hotel> chooseFilter(ArrayList<Hotel> hotelsList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which filter you want to use? (cost/stars/type/meal/pool)");
        String key = scanner.nextLine();
        switch (key) {
            case ("cost"): {
                return filter.filterByCost(hotelsList);
            }

            case ("stars"): {
                return filter.filterByStars(hotelsList);
            }

            case ("type"): {
                return filter.filterByType(hotelsList);
            }

            case ("meal"): {
                return filter.filterByMeals(hotelsList);
            }

            case ("pool"):
                return filter.filterByPool(hotelsList);

            default: {
                System.out.println("Invalid filter. choose again");
                return chooseFilter(hotelsList);
            }
        }
    }


}//end class




import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


class projectTests {

    final myDate date1 = new myDate(1, 1);
    final myDate date2 = new myDate(5, 1);
    final myDate date3 = new myDate(5, 2);


    boolean[] meals = {true, false, false}; //create meals array
    Hotel apartHotel = new ApartmentsHotel("hotel1", 500, 3, false, meals, true);
    Hotel baseHotel = new Hotel("hotel2", 1000, 1, true, meals);
    Hotel luxHotel = new LuxuryHotel("hotel3", 1500, 5, true, meals, true, true);

    ArrayList<Hotel> hotels = new ArrayList<>();


    @Test
    public void testForDate() {
        //note: Because the calendar is matrix so: Days: 0-29, Months: 0-11

        //day over 29
        assertThrows(IllegalArgumentException.class, () -> {
            new myDate(32, 8);
        });

        //month over 11
        assertThrows(IllegalArgumentException.class, () -> {
            new myDate(12, 15);
        });

        assertTrue(date1.isAfter(date2));
        assertTrue(date2.isAfter(date3));
        assertFalse(date3.isAfter(date1));

        assertEquals(date1.nightsCount(date2), 4);
        assertEquals(date2.nightsCount(date3), 30);
        assertEquals(date1.nightsCount(date3), date1.nightsCount(date2) + date2.nightsCount(date3));

        ArrayList<myDate> allDates = date1.allNights(date3);
        assertEquals(allDates.size(), date1.nightsCount(date3));

        assertThrows(IllegalArgumentException.class, () -> {
            date3.nightsCount(date2);  //date3 can not be before d2
        });

        assertThrows(IllegalArgumentException.class, () -> {
            date2.allNights(date1);  //date2 can not be before date1
        });
    }

    @Test
    public void testForRoom() {
        Room room = new Room(100, 4, true);
        assertTrue(room.isEmpty(date1, date2));  //the room empty

        room.addDates(date1, date2);            //now the room occupied
        assertFalse(room.isEmpty(date1, date2));
        assertFalse(room.isEmpty(date1, date3));
        assertTrue(room.isEmpty(date2, date3));  // empty between these date

        room.deleteDates(date1, date2);          //the room empty
        assertTrue(room.isEmpty(date1, date2));
    }

    @Test
    public void testForHotel() {

        boolean[] meals = {true, false, false}; //create meals array: breakfast exists, dinner and lunch not available
        int[] roomsStructBase = {0, 1, 2, 2, 1};    //create the structs of hotels: 1 room with 2 beds; 2 rooms with 3 beds; 2 room with 4 beds; 1 room with 5 beds;
        Hotel hotel = baseHotel;
        hotel.initializeHotel(roomsStructBase, true);

        //test for hotel's parameters
        int size = hotel.getBuild().size();
        assertEquals(hotel.getBuild().get(1).getRoomNumber(), 1);
        assertEquals(hotel.getBuild().get(5).getBeds(), 4);
        assertEquals(size, hotel.getBuild().get(size).getRoomNumber());

        assertTrue(hotel.getMeals()[Hotel.breakfast]);
        assertFalse(hotel.getMeals()[Hotel.dinner]);

        //test for invitations rooms in hotel
        int guest1 = 2, guest2 = 6;
        int[] guest3 = {3, 3, 4}; //3 rooms, 10 guests total

        //all rooms are empty
        assertTrue(hotel.hasVacantRoom(guest1, date1, date2));
        assertFalse(hotel.hasVacantRoom(guest2, date1, date2));   //hasn't room for 6 guests
        assertTrue(hotel.hasAvailableRooms(date1, date2, guest3));

        int roomNumber = hotel.getVacantRoom(guest1, date1, date2);
        hotel.addInvitation(roomNumber, date1, date2);
        assertEquals(hotel.getVacantRoom(guest1, date1, date2), 0); //not found room(=0)
        assertThrows(IllegalArgumentException.class, () -> {            // can not add invitation for room because is not exist
            hotel.addInvitation(roomNumber, date1, date2);
        });

        hotel.inviteRoom(date1, date2, guest3);
        assertThrows(IllegalArgumentException.class, () -> {             //invite another time
            hotel.inviteRoom(date1, date2, guest3);
        });
        assertDoesNotThrow(() -> hotel.inviteRoom(date2, date3, guest3)); //invite to other dates

        int cost = hotel.getCostPerNight();
        int rooms = guest3.length;
        int nights = date1.nightsCount(date2);
        int total = cost * rooms * nights;
        assertEquals(hotel.vacationCost(rooms, nights), total);

        int[][] anotherGuests = {{4}, {5}};
        assertTrue(hotel.hasAvailableRooms(date1, date2, anotherGuests[0]));       //another room for 4 is empty at these dates
        assertDoesNotThrow(() -> hotel.inviteRoom(date1, date2, anotherGuests[0]));

        int emptyRoomNumber = hotel.getVacantRoom(anotherGuests[1][0], date1, date2);
        assertThrows(IllegalArgumentException.class, () -> {                       //can not delete invitation for empty room
            hotel.cancelInvitation(emptyRoomNumber, date1, date2);
        });

    }

    @Test
    public void testForSorts() {

        //add hotels to array
        hotels.add(luxHotel);
        hotels.add(baseHotel);
        hotels.add(apartHotel);

        SortByCost sort1 = new SortByCost();
        SortByStars sort2 = new SortByStars();

        ArrayList<Hotel> expected = new ArrayList<>();

        hotels = sort1.sort(hotels); //sort by cost
        expected.add(apartHotel);
        expected.add(baseHotel);
        expected.add(luxHotel);
        assertIterableEquals(hotels, expected);
        expected.clear();

        hotels = sort2.sort(hotels); //sort by stars
        expected.add(baseHotel);
        expected.add(apartHotel);
        expected.add(luxHotel);
        assertIterableEquals(hotels, expected);
        expected.clear();
    }


    @Test
    public void testForPrimaryFilter() {
        Filter filter = new Filter(); //create filter object

        myDate d1 = new myDate(10, 10); //create dates
        myDate d2 = new myDate(17, 10);
        myDate d3 = new myDate(18, 10);


        //create the structs of hotel(according rooms)
        int[] roomsStructLux = {0, 100, 0, 0, 0};
        luxHotel.initializeHotel(roomsStructLux, true);

        //add hotels to array
        hotels.add(luxHotel);

        //create divide of guests to rooms
        int[] rooms = {2};

        //ArrayList<Hotel> copy = hotels;
        ArrayList<Hotel> expected = new ArrayList<>();

        expected = filter.primaryFilter(hotels, d1, d2, rooms);
        assertEquals(expected.size(), 1);
        assertIterableEquals(hotels, expected); //all hotels are empty - same arrays

        //make luxHotel full between d1 to d2
        for (int i = 1; i <= 100; i++) {
            luxHotel.inviteRoom(d1, d2, rooms);
        }

        //check if expected is not contain luxHotel
        expected = filter.primaryFilter(hotels, d1, d2, rooms);
        assertEquals(expected.size(), 0);

        //cancel one invitation at these date
        luxHotel.cancelInvitation(100, d1, d2);

        //check now
        expected = filter.primaryFilter(hotels, d1, d2, rooms);
        assertEquals(expected.size(), 1);
        assertEquals(expected.get(0).getName(), luxHotel.getName());

        //add invitation again
        luxHotel.addInvitation(100, d1, d2);

        //check with other dates
        expected = filter.primaryFilter(hotels, d2, d3, rooms);
        assertEquals(expected.size(), 1);
        assertEquals(expected.get(0).getName(), luxHotel.getName());


    }


}// end class



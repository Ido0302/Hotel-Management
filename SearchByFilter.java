

import java.util.ArrayList;

public interface SearchByFilter {


    /**
     * Filter according to dates, rooms and guests:
     * The function get two dates and array of rooms that each value represent the guests in every room.
     * The function return array of all hotels that have empty rooms accordance to the user's request.
     * @param hotels - all hotels in system
     * @return - the required hotels.
     */
    public ArrayList<Hotel> primaryFilter(ArrayList<Hotel> hotels, myDate beg, myDate end, int[]roomsRequest);

    /**
     * The function search all hotels that are "couplesOnly" and return array of these hotels
     * @param hotels - all hotels in system
     * @return - the required hotels.
     */
    public ArrayList<Hotel> filterByCouplesHotels(ArrayList<Hotel> hotels);

    /**
     * The function get range of cost from user.
     * The function return new list of hotels that their cost per night is in the range
     * @param hotels - all hotels in system
     * @return - the required hotels.
     */
    public ArrayList<Hotel> filterByCost(ArrayList<Hotel> hotels);

    /**
     * The function get number of stars from user.
     * The function return all hotels with this count or up
     * @param hotels - all hotels in system
     * @return - the required hotels.
     */
    public ArrayList<Hotel> filterByStars(ArrayList<Hotel> hotels);

    /**
     * The function get type of hotel from user.
     * The function return all hotels with this type
     * @param hotels - all hotels in system
     * @return - the required hotels.
     */
    public ArrayList<Hotel> filterByType(ArrayList<Hotel> hotels);

    /**
     * The function get meal (display by integer) from user.
     * The function return all hotels that have this meal.
     * @param hotels - all hotels in system
     * @return - the required hotels.
     */
    public ArrayList<Hotel> filterByMeals(ArrayList<Hotel> hotels);

    /**
     * The function return all hotels that have pool.
     * @param hotels - all hotels in system
     * @return - the required hotels.
     */
    public ArrayList<Hotel> filterByPool(ArrayList<Hotel> hotels);
    //public ArrayList<Hotel> filterBySpecial();
}

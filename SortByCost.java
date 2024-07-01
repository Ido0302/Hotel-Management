

import java.util.ArrayList;

public class SortByCost implements SearchBySort {

    public SortByCost(){} //default constructor
    /**
     * The function sort the array of hotels from the cheapest to most expensive.
     * @param hotels - all hotels in system
     * @return - the sorted array
     */
    @Override
    public ArrayList<Hotel> sort(ArrayList<Hotel> hotels) {
        ArrayList<Hotel> newList = new ArrayList<>();
        ArrayList<Hotel> copyList = new ArrayList<>();
        copyList.addAll(hotels);
        int minIndex = 0;
        while (copyList.size()>0){
            minIndex = this.minCost(copyList);
            newList.add(copyList.get(minIndex));
            copyList.remove(minIndex);
        }
        //hotels.clear();
        return newList;
    }


    /**
     * The function get array of hotels and return the index of cheapest hotel
     */
    private int minCost(ArrayList<Hotel> hotels){
        int min = hotels.get(0).getCostPerNight();
        int index = 0;
        for (int i = 1; i < hotels.size(); i++) {
            if (hotels.get(i).getCostPerNight() < min){
                min = hotels.get(i).getCostPerNight();
                index = i;
            }
        }
        return index;
    }
}

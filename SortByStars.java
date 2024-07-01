
import java.util.ArrayList;

public class SortByStars implements SearchBySort {

    public SortByStars(){} //default constructor

    /**
     * The function sort the array of hotels from the underrated to overrated.
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
            minIndex = this.minRank(copyList);
            newList.add(copyList.get(minIndex));
            copyList.remove(minIndex);
        }
        return newList;
    }


    /**
     * The function get array of hotels and return the index of hotel with minimum stars
     */
    private int minRank(ArrayList<Hotel> hotels){
        int min = hotels.get(0).getStars();
        int index = 0;
        for (int i = 1; i < hotels.size(); i++) {
            if (hotels.get(i).getStars() < min){
                min = hotels.get(i).getStars();
                index = i;
            }
        }
        return index;
    }
}

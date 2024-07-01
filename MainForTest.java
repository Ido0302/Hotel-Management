
/**
 * main for tests of functions that should to scan from user
 */

import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class MainForTest {

    public static void mainn(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean test = true;
        String result = "";


        Filter search = new Filter();           //create filter object
        boolean[] meals = {true, false, false}; //create meals array

        //create hotels
        Hotel apartHotel = new ApartmentsHotel("hotel1", 500, 3, false, meals, true);
        meals[Hotel.dinner] = true;
        Hotel baseHotel = new Hotel("hotel2", 1000, 1, true, meals);
        meals[Hotel.lunch] = true;
        Hotel luxHotel = new LuxuryHotel("hotel3", 1500, 5, true, meals, true, true);

        //create the structs of hotels(according rooms)
        int[] roomsStructBase = {0,10,15,15,10};
        int[] roomsStructApart = {0,10,10,15,15};
        int[] roomsStructLux = {0,100,0,0,0};      //couples hotel
        baseHotel.initializeHotel(roomsStructBase,true);
        apartHotel.initializeHotel(roomsStructApart,true);
        luxHotel.initializeHotel(roomsStructLux, true);

        //add hotels to array
        ArrayList<Hotel> hotels = new ArrayList<>();
        hotels.add(luxHotel);
        hotels.add(baseHotel);
        hotels.add(apartHotel);

        //create auxiliary array
        ArrayList<Hotel> copy = hotels;


        //test for filter by cost

        System.out.println("For test choose: min=800, max=2000");
        copy = search.filterByCost(copy); //filter by cost: 800-2000
        for (int i = 0; i < copy.size(); i++) {
            int cost = copy.get(i).getCostPerNight();
            if (cost < 800 || cost > 2000)
                test = false;
        }
        if(copy.size()==0)
            System.out.println("Not found any hotel");
        result = (test) ? "succeeded!" : "failed.";
        System.out.println(result);

        //for the next test (needed after every test)
        copy.clear();
        copy.addAll(hotels);
        test = true;

        System.out.println("For test choose: min=0, max=750");
        copy = search.filterByCost(copy); //filter by cost: 0-750
        for (int i = 0; i < copy.size(); i++) {
            int cost = copy.get(i).getCostPerNight();
            if (cost > 750)
                test = false;
        }

        if(copy.size()==0)
            System.out.println("Not found any hotel");
        result = (test) ? "succeeded!" : "failed.";
        System.out.println(result);
        copy.clear();
        copy.addAll(hotels);
        test = true;


        //test for filter by stars

        System.out.println("For test choose: 2");
        copy = search.filterByStars(copy); //filter by stars: 2
        for (int i = 0; i < copy.size(); i++) {
            int rank = copy.get(i).getStars();
            if (rank < 2)
                test = false;
        }

        if(copy.size()==0)
            System.out.println("Not found any hotel");
        result = (test) ? "succeeded!" : "failed.";
        System.out.println(result);
        copy.clear();
        copy.addAll(hotels);
        test = true;

        System.out.println("For test choose: 4");
        copy = search.filterByStars(copy); //filter by stars: 4
        for (int i = 0; i < copy.size(); i++) {
            int rank = copy.get(i).getStars();
            if (rank < 4)
                test = false;
        }

        if(copy.size()==0)
            System.out.println("Not found any hotel");
        result = (test) ? "succeeded!" : "failed.";
        System.out.println(result);
        copy.clear();
        copy.addAll(hotels);
        test = true;

        //test for filter by type
        System.out.println("For test choose: luxury");
        copy = search.filterByType(copy); //filter by type: Luxury
        for (int i = 0; i < copy.size(); i++) {
            String type = copy.get(i).getType();
            if (!type.equalsIgnoreCase("luxury"))
                test = false;
        }

        if(copy.size()==0)
            System.out.println("Not found any hotel");
        result = (test) ? "succeeded" : "failed";
        System.out.println(result);
        copy.clear();
        copy.addAll(hotels);
        test = true;

        System.out.println("For test choose: apartments");
        copy = search.filterByType(copy); //filter by type: apartments
        for (int i = 0; i < copy.size(); i++) {
            String type = copy.get(i).getType();
            if (!type.equalsIgnoreCase("Apartments"))
                test = false;
        }

        if(copy.size()==0)
            System.out.println("Not found any hotel");
        result = (test) ? "succeeded" : "failed";
        System.out.println(result);
        copy.clear();
        copy.addAll(hotels);
        test = true;

        //test for filter by meal

        System.out.println("For test choose: 2 (dinner)");
        copy = search.filterByMeals(copy); //filter by meal: dinner(=2)
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i).getMeals()[Hotel.dinner]==false)
                test = false;
        }

        if(copy.size()==0)
            System.out.println("Not found any hotel");
        result = (test) ? "succeeded" : "failed";
        System.out.println(result);
        copy.clear();
        copy.addAll(hotels);
        test = true;


        System.out.println("For test choose: 1(lunch)");
        copy = search.filterByMeals(copy); //filter by meal: lunch(=1)
        for (int i = 0; i < copy.size(); i++) {
            if (copy.get(i).getMeals()[Hotel.dinner]==false)
                test = false;
        }

        if(copy.size()==0)
            System.out.println("Not found any hotel");
        result = (test) ? "succeeded" : "failed";
        System.out.println(result);
        copy.clear();
        copy.addAll(hotels);
        test = true;

        //test for pool

        System.out.println("Test for filter 'pool'");
        copy = search.filterByPool(copy); //sort by pool
        for (int i = 0; i < copy.size(); i++) {
            if(!copy.get(i).hasPool())
                test = false;
        }

        if(copy.size()==0)
            System.out.println("Not found any hotel");
        result = (test) ? "succeeded" : "failed";
        System.out.println(result);
        copy.clear();
        copy.addAll(hotels);
        test = true;

        //test for isCouplesHotel

        System.out.println("Test for filter 'isCouplesHotel'");
        copy = search.filterByCouplesHotels(copy); //get only couple's hotels
        for (int i = 0; i < copy.size(); i++) {
            if(!copy.get(i).isCouplesOnly())
                test = false;
        }

        if(copy.size()==0)
            System.out.println("Not found any hotel");
        result = (test) ? "succeeded" : "failed";
        System.out.println(result);
        copy.clear();
        copy.addAll(hotels);
        test = true;




    }
}// end main


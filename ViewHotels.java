

import java.util.ArrayList;

public class ViewHotels {

    public ViewHotels() {} //default constructor


    public void printHotel(Hotel h) {
        System.out.println(h.getName() + " is " + h.getType() + " hotel with " + h.getStars() + " stars");
        if (h.isCouplesOnly())
            System.out.println("The hotel is just for couples");
        if (h.getMeals()[Hotel.lunch])
            System.out.println("There are also breakfast, lunch and dinner include in the hotel");
        else if (h.getMeals()[Hotel.dinner])
            System.out.println("There are also breakfast and dinner include in the hotel");
        else if (h.getMeals()[Hotel.breakfast])
            System.out.println("There are also breakfast include in the hotel");
        if (h.hasPool())
            System.out.println("There is a pool in the hotel");
        System.out.println("The cost per night for 1 room is: "+h.getCostPerNight());

    }

    public void printLuxuryHotel(LuxuryHotel h) {
        printHotel(h); //the function above
        if (h.hasSpa())
            System.out.println("The hotel has spa");
        if (h.hasRestaurant())
            System.out.println("The hotel has restaurant");
    }

    public void printApartmentsHotel(ApartmentsHotel h) {
        printHotel(h); //the function above
        if (h.hasJym())
            System.out.println("Jym include in hotel");
    }

    public void printAll(ArrayList<Hotel> hotels) {
        for (int i = 0; i < hotels.size(); i++) {
            if (hotels.get(i).getType().equalsIgnoreCase("apartments")) {
                ApartmentsHotel h = (ApartmentsHotel) hotels.get(i);
                printApartmentsHotel(h);
            } else if (hotels.get(i).getType().equalsIgnoreCase("luxury")) {
                LuxuryHotel h = (LuxuryHotel) hotels.get(i);
                printLuxuryHotel(h);
            } else
                printHotel(hotels.get(i));
            System.out.println("***************");
        }
    }

    //print the details of invitation
    public void printVacation(Hotel h, myDate start, myDate end, int rooms){

        System.out.print("We wait for you in " + h.getName()+ " from ");
        start.printDate();
        System.out.print(" to ");
        end.printDate();
        int nights = start.nightsCount(end);
        System.out.println("\nYour vacation cost is "+h.vacationCost(rooms,nights)+"Bhat for "+rooms+" rooms along to "+nights+" nights.");
        h.getBuild().get(1).printRoom();
    }


}//end class


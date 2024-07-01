import java.util.ArrayList;
import java.util.Scanner;

public class Filter implements SearchByFilter {

    public Filter(){} //default constructor
    @Override
    public ArrayList<Hotel> primaryFilter(ArrayList<Hotel> hotels, myDate beg, myDate end, int[]roomsRequest){
        ArrayList<Hotel> newList = new ArrayList<>();
        for (int i = 0; i < hotels.size(); i++) {
            if(hotels.get(i).hasAvailableRooms(beg, end, roomsRequest))
                newList.add(hotels.get(i));
        }
        return newList;
    }

    @Override
    public ArrayList<Hotel> filterByCouplesHotels(ArrayList<Hotel> hotels) {
        ArrayList<Hotel> newList = new ArrayList<>();
        for (int i = 0; i < hotels.size(); i++) {
            if(hotels.get(i).isCouplesOnly())
                newList.add(hotels.get(i));
        }
        return newList;
    }


    @Override
    public ArrayList<Hotel> filterByCost(ArrayList<Hotel> hotels) {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        System.out.println("What the minimum cost per night you want to pay for night? ");
        int minCost = scanner.nextInt();
        System.out.println("What the maximum cost per night you want to pay for night? ");
        int maxCost = scanner.nextInt();
        ArrayList<Hotel> newList = new ArrayList<>();
        for (int i = 0; i < hotels.size(); i++) {
            int cost = hotels.get(i).getCostPerNight();
            if(cost >= minCost && cost <= maxCost)
                newList.add(hotels.get(i));
        }
        return newList;
    }

    @Override
    public ArrayList<Hotel> filterByStars(ArrayList<Hotel> hotels) {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        System.out.print("What the minimum stars you want to search? ");
        int rank = scanner.nextInt();

        while (!isValid) {
            if (rank >= 1 && rank <= 5)
                isValid = true;
            else {
                System.out.println("Invalid input. Please choose again");
                rank = scanner.nextInt();
                scanner.nextLine(); // clean buffer
            }
        }
        ArrayList<Hotel> newList = new ArrayList<>();
        for (int i = 0; i < hotels.size(); i++) {
            if(rank <= hotels.get(i).getStars())
                newList.add(hotels.get(i));
        }
        return newList;
    }


    @Override
    public ArrayList<Hotel> filterByType(ArrayList<Hotel> hotels) {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        System.out.print("Which type of hotels you want to look for? (basic/luxury/apartments) ");
        String type = scanner.nextLine();
        while (!isValid) {
            if (type.equalsIgnoreCase("basic")||type.equalsIgnoreCase("luxury")||type.equalsIgnoreCase("apartments"))
                isValid = true;
            else {
                scanner.nextLine(); // clean buffer
                System.out.println("Invalid input. Please choose again");
                type = scanner.nextLine();
            }
        }
        ArrayList<Hotel> newList = new ArrayList<>();
        for (int i = 0; i < hotels.size(); i++) {
            if(hotels.get(i).getType().equalsIgnoreCase(type)) {
                newList.add(hotels.get(i));
            }
        }
        return newList;
    }

    @Override
    public ArrayList<Hotel> filterByMeals(ArrayList<Hotel> hotels) {
        Scanner scanner = new Scanner(System.in);
        boolean isValid = false;
        System.out.print("Please choose one option: 0 for breakfast/1 for lunch/2 for dinner ");
        int meal = scanner.nextInt();
        while (!isValid) {
            if (meal >= 0 && meal <= 2)
                isValid = true;
            else {
                System.out.println("Invalid input. Please choose again");
                meal = scanner.nextInt();
                //scanner.nextLine(); // clean buffer
            }
        }
        ArrayList<Hotel> newList = new ArrayList<>();
        for (int i = 0; i < hotels.size(); i++) {
            if(hotels.get(i).getMeals()[meal])
                newList.add(hotels.get(i));
        }
        return newList;
    }

    @Override
    public ArrayList<Hotel> filterByPool(ArrayList<Hotel> hotels) {
        ArrayList<Hotel> newList = new ArrayList<>();
        for (int i = 0; i < hotels.size(); i++) {
            if(hotels.get(i).hasPool())
                newList.add(hotels.get(i));
        }
        return newList;
    }


}// end class

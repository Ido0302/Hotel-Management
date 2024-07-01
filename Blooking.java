/**
 * class represents Blooking
 * responsible on the contact with the user and him invitation
 */

import java.util.ArrayList;
import java.util.Scanner;


public class Blooking {
    private static Blooking instance;


    private Blooking() {
    }

    public static Blooking getInstance() {
        if (instance == null)
            instance = new Blooking();
        return instance;
    }

    public void playBlooking() {
        System.out.println("Welcome to Blooking!");
        HotelManagement management = HotelManagement.getInstance(); //object for the database of the system
        ViewHotels view = new ViewHotels();                         //object for the view and all prints for user
        SortByCost costsSort = new SortByCost();                    //object for sort according costs
        SortByStars starsSort = new SortByStars();                  //object for sort according stars

        Scanner scanner = new Scanner(System.in);
        ArrayList<Hotel> suggestedHotels = management.getHotels();  //create copy of the array list of hotel of the system

        //Creature start date
        System.out.println("When do you want to start your vacation? ");
        System.out.print("choose day (0...29) ");
        int day = scanner.nextInt();
        System.out.print("choose month (0 for Jan...11 for Dec) ");
        int month = scanner.nextInt();
        myDate start = null;

        //check if the parameters are valid
        try {
            start = new myDate(day, month);
        } catch (Exception e) {
            System.out.println("Invalid date");
        }

        //Creature and end date
        System.out.println("When do you want to finish your vacation? ");
        System.out.print("choose day (0...29) ");
        day = scanner.nextInt();
        System.out.print("choose month (0 for Jan...11 for Dec) ");
        month = scanner.nextInt();
        myDate end = null;

        //check if the parameters are valid
        try {
            end = new myDate(day, month);
        } catch (Exception e) {
            System.out.println("Invalid date");
        }

        if (start == null || end == null)
            System.exit(0);

        //get count of guests from the user
        System.out.print("How many guests are you? ");
        int guests = scanner.nextInt();

        //all hotels include rooms with at least 2 beds
        if (guests < 2)
            throw new IllegalArgumentException("You are too little guests");

        //creature array of rooms that each room contain count of guests
        System.out.println("How many rooms do you want? ");
        int countRooms = scanner.nextInt();
        int[] rooms = new int[countRooms];
        if (countRooms == 1)
            rooms[0] = guests;
        else {
            for (int i = 0; i < countRooms - 1; i++) {
                System.out.println("How many guests in room " + (i + 1) + "?");
                int visitors = scanner.nextInt(); //get count of guests in each room
                rooms[i] = visitors;
                guests -= visitors;
            }
            rooms[countRooms - 1] = guests;
        }

        //The primary filter - according dates, guests and room
        suggestedHotels = management.getFilter().primaryFilter(suggestedHotels, start, end, rooms);

        //check the list after filter
        if (suggestedHotels.size() == 0) {
            System.out.println("There are not vacant hotels for you.");
            if (renewalOrder())
                playBlooking();
            System.exit(0);
        }
        System.out.println("The hotels that available at yor dates are: ");
        view.printAll(suggestedHotels);

        scanner.nextLine(); // clean buffer
        String ans = "";

        //while the user not yet find hotel
        while (!ans.equalsIgnoreCase("OK")) {
            System.out.println("Do you want to use sort or filter? (choose s for sort, f for filter, skip to continue)");
            String key = scanner.nextLine();

            //user choose to sort and need to choose which sort
            if (key.equals("s")) {
                System.out.println("What the key sort you want to use? Cost/Stars");
                key = scanner.nextLine();
                if (key.equalsIgnoreCase("cost")) {
                    management.setSort(costsSort);
                    suggestedHotels = management.sort(suggestedHotels); //sort by cost
                }
                if (key.equalsIgnoreCase("stars")) {
                    management.setSort(starsSort);
                    suggestedHotels = management.sort(suggestedHotels); //sort by stars
                }
            }

            //user choose filter and need to choose which filter
            if (key.equals("f")) {
                suggestedHotels = management.chooseFilter(suggestedHotels);

                //check the list after filter
                if (suggestedHotels.size() == 0) {
                    System.out.println("There are not vacant hotels for you.");
                    if (renewalOrder())
                        playBlooking();
                    System.exit(0);
                }
            }

            //display for user the list after the sorts and filters
            System.out.println("These are the hotels that may to interest you:");
            view.printAll(suggestedHotels);

            //end of loop. the user need to choose from 4 options: 1.choose hotel. 2.continue to search. 3.renew invitation. 4.exit from system
            System.out.println("To make a deal write 'OK'. To continue search write 'con'. To renewal the order write 're'. To exit write 'exit'. ");
            ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("re")) {
                playBlooking();
                System.exit(0);
            } else if (ans.equalsIgnoreCase("exit")) {
                System.exit(0);
            }
        }

        //user choose hotel
        System.out.println("Which hotel do you want to stay at? Choose name from the list above ");
        String name = scanner.nextLine();
        Hotel chosenHotel = management.getHotelByName(name); //get the hotel from list
        while (chosenHotel == null) {
            System.out.println("Invalid name. Choose again");
            name = scanner.nextLine();
            chosenHotel = management.getHotelByName(name);
        }

        try {
            chosenHotel.inviteRoom(start, end, rooms);                 //invite room
            view.printVacation(chosenHotel, start, end, rooms.length); //print the details of invitation

        } catch (IllegalArgumentException e) {
            System.out.println("Invitation failed");
        }

        //check if make new invitation
        if (renewalOrder())
            playBlooking();
        System.exit(0);

        System.out.println("See you soon at Blooking!");

    }


    /**
     * The function ask the user if he want to make new invitation and return true if he agrees, else false
     */
    public boolean renewalOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("To renewal your order, press enter.\nTo exit write exit");
        String ans = scanner.nextLine();
        if (ans.equalsIgnoreCase("exit"))
            return false;
        return true;
    }


}//end class
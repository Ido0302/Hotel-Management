import java.util.ArrayList;

public class Room {
    private int roomNumber;
    private int beds;
    private boolean balcony;
    private boolean [][] calendar;


    public Room(int roomNumber, int beds, boolean balcony) {
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.balcony = balcony;

        calendar = new boolean [12][30];
        for (int i = 0; i < calendar.length; i++) {
            for (int j = 0; j < calendar[i].length; j++) {
                calendar[i][j] = true;
            }
        }
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public int getBeds() {
        return this.beds;
    }

    public boolean hasBalcony() {
        return balcony;
    }

    public boolean [][] getCalendar() {
        return calendar;
    }

    public void printRoom() {
        String ans = "";
        if (hasBalcony())
            ans += "Exists balcony.";
        else
            ans += "Not exists balcony.";
        System.out.println(ans);
    }


    /**
     * get 2 dates and return true if the room empty in all dates between these dates
     */
    public boolean isEmpty(myDate d1, myDate d2) {
        ArrayList<myDate> allDates = d1.allNights(d2);
        for (int i = 0; i < allDates.size(); i++) {
            myDate d = allDates.get(i);
            if (!this.calendar[d.getMonth()][d.getDay()])
                return false;
        }
        return true;
    }

    /**
     * get 2 dates and change the value in calendar to false(not empty) in all dates between these dates
     */
    public void addDates(myDate d1, myDate d2) {
        ArrayList<myDate> allDates = d1.allNights(d2);
        for (int i = 0; i < allDates.size(); i++) {
            myDate d = allDates.get(i);
            this.calendar[d.getMonth()][d.getDay()] = false;
        }
    }


    /**
     * get 2 dates and change the value in calendar to true(empty) in all dates between these dates
     */
    public void deleteDates(myDate d1, myDate d2) {
        ArrayList<myDate> allDates = d1.allNights(d2);
        for (int i = 0; i < allDates.size(); i++) {
            myDate d = allDates.get(i);
            this.calendar[d.getMonth()][d.getDay()] = true;
        }
    }






}//end class


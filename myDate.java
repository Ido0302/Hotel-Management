import java.util.ArrayList;

public class myDate {
    private int day;
    private int month;

    public myDate(int day, int month){
        if(day < 0 || day > 29 || month < 0 || month > 11 )
            throw new IllegalArgumentException("invalid date");
        this.day = day;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    /**
     * get other date and return the nights between other to current date (assume other is after current)
     */
    public int nightsCount(myDate other){
        //if other is before the current date (or sames)
        if(!this.isAfter(other))
            throw new IllegalArgumentException("Invalid dates");

        if(other.getMonth()==this.month)
            return other.getDay()-this.day; //count of nights at same month
        return (30-this.day + 1) + 30*(other.month-this.month-1) + (other.getDay()-1); // count of nights at first month, plus count of nights in months between the first and last month, plus count of nights at last month
    }



    /**
     * get other date and return true if it's after the current date. else return false
     */
    public boolean isAfter(myDate other){
        if(other.getMonth() < this.month)
            return false;
        if(other.getMonth()==this.month && other.getDay()<=this.day)
            return false;
        return true;
    }


    public void printDate(){
        System.out.print(this.day+"/"+this.month);
    }


    /**
     * get date 'end' and return array list of all dates between the current date to 'end' (without 'end')
     * @param end - date of end of holiday
     * @return - ArrayList of dates
     */
    public ArrayList<myDate> allNights(myDate end){
        ArrayList<myDate> allDates = new ArrayList<>();
        int d = this.day;
        int m = this.month;
        myDate beg = new myDate(d,m);

        //if end is before beg (or sames)
        if(!this.isAfter(end))
            throw new IllegalArgumentException("Invalid dates");

        allDates.add(beg);
        int length = beg.nightsCount(end);
        for (int i = 1; i < length; i++) {
            d++;
            if(d==30) {
                d = 0;
                m++;
            }
            myDate date = new myDate(d,m);
            allDates.add(date);
        }
        return allDates;
    }
}

/**
 * class represent apartment
 */
public class Apartment extends Room {
    private boolean kitchen;

    private boolean wifi;

    public Apartment(int roomNumber, int beds, boolean balcony, boolean kitchen, boolean wifi) {
        super(roomNumber, beds, balcony);
        this.kitchen = kitchen;
        this. wifi = wifi;
    }

    public boolean hasKitchen() {
        return kitchen;
    }
    public boolean hasWifi() {
        return wifi;
    }

    public void printRoom() {
        super.printRoom();
        if (hasKitchen())
            System.out.println("The apartment contain kitchen.");
        if(hasWifi())
            System.out.println("Wifi include too.");
    }
}

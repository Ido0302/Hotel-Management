public class Premium extends Room {
    private boolean jacuzzi;
    private boolean seaView;

    public Premium(int roomNumber, int beds, boolean jacuzzi, boolean seaView) {
        super(roomNumber, beds, true);
        this.jacuzzi = jacuzzi;
        this. seaView = seaView;
    }

    public boolean hasJacuzzi() {
        return jacuzzi;
    }

    public boolean hasSeaView() {
        return seaView;
    }

    public void printRoom() {
        super.printRoom();
        if (hasJacuzzi())
            System.out.println("The suite contain jacuzzi.");
        if(hasSeaView())
            System.out.println("There is also sea view from the balcony.");
    }
}

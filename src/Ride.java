public class Ride {
    private String name;
    private String pickupAddress;
    private String destinationAddress;
    private double cost;
    private String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public double getCost() {
        return cost;
    }

    public String getDate() {
        return date;
    }

    public Ride(String name, String pickupAddress, String destinationAddress, double cost, String date) {
        this.pickupAddress = pickupAddress;
        this.destinationAddress = destinationAddress;
        this.cost = cost;
        this.date = date;
        this.name = name;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "pickupAddress='" + pickupAddress + '\'' +
                ", destinationAddress='" + destinationAddress + '\'' +
                ", cost=" + cost +
                ", date='" + date + '\'' +
                '}';
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

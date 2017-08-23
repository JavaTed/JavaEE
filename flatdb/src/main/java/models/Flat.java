package models;

public class Flat {
    private int area;
    private byte roomNumber;
    private double price;
    private String city;
    private String street;
    private String building;
    private String flatNumber;

    public Flat() {
    }

    public Flat(int square, byte roomNumber, double price, String city, String street, String building, String flatNumber) {
        this.area = square;
        this.roomNumber = roomNumber;
        this.price = price;
        this.city = city;
        this.street = street;
        this.building = building;
        this.flatNumber = flatNumber;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public byte getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(byte roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }
}

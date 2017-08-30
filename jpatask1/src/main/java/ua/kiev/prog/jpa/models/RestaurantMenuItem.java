package ua.kiev.prog.jpa.models;

import javax.persistence.*;

@Entity
@Table(name="restaurantmenu")
@NamedQueries(@NamedQuery(name="Item.AllItems", query = "SELECT c FROM RestaurantMenuItem c"))
public class RestaurantMenuItem {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="dishname", nullable=false)
    private String dishName;
    @Column(name="price", nullable=false)
    private Double price;
    @Column(name="weight", nullable=false)
    private Double weight;

    @Column(name="discountpercent", nullable=false)
    private double discountPercent;

    public RestaurantMenuItem() {
    }

    public RestaurantMenuItem(String dishName, Double price, Double weight, double discountPercent) {
        this.dishName = dishName;
        this.price = price;
        this.weight = weight;
        this.discountPercent = discountPercent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return "RestaurantMenuItem{" +
                "id#=" + id +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", discountPercent=" + discountPercent +
                '}';
    }
}

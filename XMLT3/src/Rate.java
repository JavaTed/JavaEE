import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Rate {
    @XmlAttribute
    private String id;
    @XmlElement(name="Name")
    private String name;
    @XmlElement(name="Rate")
    private double rate;
    @XmlElement(name="Date")
    private String date;
    @XmlElement(name="Time")
    private String time;
    @XmlElement(name="Ask")
    private String ask;
    @XmlElement(name="Bid")
    private String bid;

    public String getId() {
        return id;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "id='" + id + '\'' +
                ", Name='" + name + '\'' +
                ", Rate=" + rate +
                ", Date='" + date + '\'' +
                ", Time='" + time + '\'' +
                ", Ask='" + ask + '\'' +
                ", Bid='" + bid + '\'' +
                '}';
    }
}

import javax.xml.bind.annotation.XmlElement;

public class Results {
    @XmlElement
    private Rate[] rate;

    public Rate[] getRate() {
        return rate;
    }
}

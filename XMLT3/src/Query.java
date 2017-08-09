import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="query")
public class Query {
    private String created;
    @XmlElement
    private String lang;
    @XmlElement
    private Results results;

    public Results getResults() {
        return results;
    }
}

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Person {
    private String name;
    private String surname;
    private String[] phones;
    @SerializedName("sites")
    private String[] site;
    private Address address;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone=" + Arrays.toString(phones) +
                ", cites=" + Arrays.toString(site) +
                ",\n adress=" + address +
                '}';
    }
}

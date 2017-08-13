import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("GSON start");
        JSONService<Person> js = new JSONService<>();
        File fIn = new File("c:\\JavaCourse\\files\\person.jtxt");
        File fOut = new File("c:\\JavaCourse\\files\\person_out.jtxt");
        Person p = (Person)js.fromJSON(fIn,Person.class);
        js.toJSON(fOut,p);



    }
}

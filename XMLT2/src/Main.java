import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

class JSONService<T>{
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public T fromJSON(File f, Class<? extends T> cl){
        try {
            T jsonPerson = gson.fromJson(new JsonReader(new FileReader(f)), cl);
            System.out.println("FROM:\n "+jsonPerson);
            return jsonPerson;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void toJSON(File f,T p){
        if (p == null)
            return;
        String json = gson.toJson(p);
        System.out.println("TO:\n "+json);
        try {
            PrintWriter pw = new PrintWriter(f);
            pw.print(json);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

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

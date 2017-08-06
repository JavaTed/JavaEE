import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

@SaveTo(fileName = "c:\\JavaCourse\\TextCont.txt")
public class TextContainer {
    private String text;

    public TextContainer(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Saver
    public void saveText(String fn){
        System.out.println("Save '"+getText()+"' to "+fn);

        try {
            PrintWriter pw = new PrintWriter(fn);
            pw.println(getText());
            pw.flush();

            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

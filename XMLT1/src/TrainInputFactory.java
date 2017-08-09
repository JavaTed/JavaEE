import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TrainInputFactory {
    private static final String datePattern = "d.M.y";
    private static final String timePattern = "[0-9]{2,2}:[0-9]{2,2}";
    private static final String namePattern = "^[A-Z]{1,}[a-zA-Z- ]{1,}$";
    private static Train trainBuffer;

    private static LocalDate parseDate(String dateString){
        DateTimeFormatter sf = DateTimeFormatter.ofPattern(datePattern);
        return LocalDate.parse(dateString,sf);
    }

    public static String getTimePattern() {
        return timePattern;
    }

    public static String getDatePattern() {
        return datePattern;
    }

    public static String getNamePattern() {
        return namePattern;
    }

    static public Train getEnteredTrain() {
        return trainBuffer;
    }

    static public boolean isTrainEntered(BufferedReader d){
        trainBuffer = null;

        String ids = KeyboardReader.readStrInfo(d,"Train's number","[0-9]+",true);
        if (ids != null) {
            int id = Integer.parseInt(ids);
            String from = KeyboardReader.readStrInfo(d,"From",getNamePattern());
            String to = KeyboardReader.readStrInfo(d,"To",getNamePattern());
            LocalDate date = getEnteredDate(d,"Departure Date ("+getDatePattern()+")");

            String departure = KeyboardReader.readStrInfo(d,"Departure Time (hh:mm)",getTimePattern());

            trainBuffer = new Train(id, from, to, date.toString(), departure);
            return true;
        }
        trainBuffer = null;
        return false;
    }

    private static LocalDate getEnteredDate(BufferedReader d, String hint) {

        String dateString = KeyboardReader.readStrInfo(d,hint,"");
        LocalDate sd = null;
        try {
            sd = parseDate(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return getEnteredDate(d, hint);
        }
        return sd;

    }
}

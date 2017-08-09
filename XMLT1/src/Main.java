import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Predicate;


public class Main {
    private static Trains trList = new Trains();

    private static BufferedReader d = new BufferedReader(new InputStreamReader(System.in));

    private static XMLService<Trains> xt = new XMLService<>(Trains.class);

    private static String getMenuChoice(){
        return KeyboardReader.readStrInfo(d,getMenu(),"^(0|1|2|3|4|5|6)$");
    }

    static private void doEnter(){
        while (TrainInputFactory.isTrainEntered(d)) {
            trList.add(TrainInputFactory.getEnteredTrain());
        }
    }

    static private void showTrainList(){
        if (trList.getTrains().size() == 0){
            System.out.println("List is empty");
            return;
        }
        trList.getTrains().stream()
                .forEach(System.out::println);
    }

    static private void showFilteredTrainList(){
        LocalTime lowBound = LocalTime.parse(KeyboardReader.readStrInfo(d,"Enter the low time bound (hh:mm)",TrainInputFactory.getTimePattern()), DateTimeFormatter.ISO_TIME);
        LocalTime highBound = LocalTime.parse(KeyboardReader.readStrInfo(d,"Enter the high time bound (hh:mm)",TrainInputFactory.getTimePattern()), DateTimeFormatter.ISO_TIME);

        trList.getTrains().stream()
                .filter(departureTimeBetween(lowBound,highBound))
                .forEach(System.out::println);
    }

    public static Predicate<Train> departureTimeBetween(LocalTime lowBound,LocalTime highBound){
        return t -> t.getDepartureTime().compareTo(lowBound)>=0 && t.getDepartureTime().compareTo(highBound)<=0;
    }

    private static String getFileWithDefault(String s) {
        return Optional.ofNullable(KeyboardReader.readStrInfo(d,"XML File Name (DEFAULT "+s+")","",true)).orElse(s);
    }

    private static void doRemove() {
        String trIds = KeyboardReader.readStrInfo(d,"Enter the Train id","[0-9]+",true);
        if (trIds != null) {
            int trId = Integer.parseInt(trIds);
            Train train = trList.getTrains()
                    .stream()
                    .filter(t -> t.getId()==trId)
                    .findFirst()
                    .orElse(null);
            if (train != null) {
                trList.getTrains().remove(train);
            } else{
                System.out.println("No such train in list");
            }
        }
    }

    private static String getMenu(){
        return new StringBuffer()
                .append("Your choice: ")
                .append(" (0) Exit;")
                .append(" (1) Show Whole Train List;")
                .append(" (2) Filter train list by departure time")
                .append(" (3) Enter new Train")
                .append(" (4) Remove train;")
                .append(" (5) Load list from XML")
                .append(" (6) Save list to XML")
                .toString();
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        String mi;
        while (!(mi = getMenuChoice()).equals("0")) {
            switch (mi){
                case "1":
                    showTrainList();
                    break;
                case "2":
                    showFilteredTrainList();
                    break;
                case "3":
                    doEnter();
                    break;
                case "4":
                    doRemove();
                    break;
                case "6":
                    String toFile = getFileWithDefault("trains_out.xml");
                    xt.marshall(trList,toFile);
                    break;
                case "5":
                    String fromFile = getFileWithDefault("trains.xml");
                    System.out.println("fromFile="+fromFile);
                    Trains tmpTrList = xt.unmarshall(fromFile);
                    if (tmpTrList != null){
                        System.out.println(tmpTrList.getTrains());
                        trList.Merge(tmpTrList);}
                    break;
            }
        }
        System.exit(0);
    }

}

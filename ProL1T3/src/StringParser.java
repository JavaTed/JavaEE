import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StringParser {
    private String parString;
    private Map<String,String> parList = new HashMap<>();

    StringParser(String parString) {
        this.parString = parString;
        parList.clear();
        parList = Arrays.stream(this.parString.replaceAll(";$","").replaceAll("((;[a-zA-Z0-9_]{1,}=))",";$2").split(";;"))
                .map(t->t.split("="))
                .collect(Collectors.toMap(t->t[0],t->t[1]));
    }

    public Map<String,String> getParList(){
        return parList;
    }

}

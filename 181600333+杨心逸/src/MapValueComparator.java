import java.util.Comparator;
import java.util.Map;

public class MapValueComparator implements Comparator<Map.Entry<String, String>> {

    @Override
    public int compare(Map.Entry<String, String> me1, Map.Entry<String, String> me2) {
        int flag=me2.getValue().compareTo(me1.getValue());
        if(flag==0){
            flag=me1.getKey().compareTo(me2.getKey());
        }
        return flag;
        //return me1.getValue().compareTo(me2.getValue());
    }
}
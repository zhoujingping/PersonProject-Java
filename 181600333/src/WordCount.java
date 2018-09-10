import java.util.HashMap;
import java.util.Map;

/*import static com.siberia.demo.lib.*;*/


public class WordCount {
    public static void main(String[] args) {
        String pathname = args[0];
        int characters = 0;
        int words = 0;
        int lines = 0;
        Map<String, String> map = new HashMap<String, String>();
        /*查询&统计*/
        characters = lib.countChar(pathname);
        words = lib.countWord(pathname);
        lines = lib.countLines(pathname);
        map = lib.countFrequency(pathname);
        /*输出结果*/
        lib.printFile(characters, words, lines, map);
        System.out.println("completed");
    }
}

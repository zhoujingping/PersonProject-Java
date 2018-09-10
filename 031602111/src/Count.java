import java.io.*;
import java.util.*;

public class Count {
    //初始化字符数，有效行数，单词数
    private static int cnum = 0;
    private static int lnum = 0;
    private static int wnum = 0;
    private static String word = new String();
    private static Map  ma= new HashMap();

    public int ccount(File fileIn){
        int len = 0;
        try{
            BufferedReader bf = new BufferedReader(new FileReader(fileIn));
            int byte_char  = -1;
            Boolean flag = false;
            while ((byte_char = bf.read()) >= 0 ){
                cnum++;
                if((byte_char != 10)&&(byte_char != 13)&&(byte_char != 32)){
                    len = Count.judge(byte_char,len,ma);
                    flag = true;
                }else if ((byte_char == 10)||(byte_char == 32)){
                    len = Count.judge(13,len,ma);
                }else {
                    if(flag){
                        len = Count.judge(13,len,ma);
                        lnum++;
                        flag = false;
                    }
                }
            }
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lnum++;
        return cnum;
    }

    public int lcount(){
        return this.lnum;
    }

    public int wcount(){
        return this.wnum;
    }

    public List<HashMap.Entry<String, Integer>> wordlist(){
        //单词排序
        List<HashMap.Entry<String, Integer>> wordList = WordSort();
        return wordList;
    }


    //检测单词的函数
    public static int judge(int byte_num, int len, Map m){
        if(len>=4){
            if((byte_num>=65&&byte_num<=90)){
                word = word + (char)(byte_num+32);
                len++;
            }else if((byte_num>=97&&byte_num<=122)||(byte_num>=48&&byte_num<=57)){
                word = word+ (char)(byte_num);
                len++;
            }else{
                ma = Maps(m,word);
                wnum++;
                word = new String();;
                len = 0;
            }
            return len;
        }else{
            if(byte_num>=65&&byte_num<=90){
                word = word+ (char)(byte_num+32);
                len++;
            }else if(byte_num>=97&&byte_num<=122){
                word = word+ (char)(byte_num);
                len++;
            }else{
                word = new String();;
                len = 0;
            }
            return len;
        }
    }
    //更新字典的函数
    public static Map Maps(Map m,String s){
        if(m.containsKey(s)){
            int n = (int)m.get(s);
            n++;
            m.put(s,n);
        }else{
            m.put(s,1);
        }
        return m;
    }

    //单词排序
    public static List<HashMap.Entry<String, Integer>> WordSort(){
        List<HashMap.Entry<String, Integer>> wordList = new ArrayList<HashMap.Entry<String, Integer>>(ma.entrySet());

        Comparator<Map.Entry<String, Integer>> com = new Comparator<Map.Entry<String, Integer>>(){

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue()==o2.getValue())
                    return o1.getKey().compareTo(o2.getKey());//字典序
                return o2.getValue()-o1.getValue();//从大到小
            }
        };
        wordList.sort(com);
        return wordList;
    }


}

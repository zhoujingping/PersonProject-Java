import java.io.*;
import java.util.*;

public class Count {
    //初始化全局变量字符数，有效行数，单词数，单词，单词Map
    private static int cnum = 0;
    private static int lnum = 0;
    private static int wnum = 0;
    private static String word = new String();
    private static Map  ma= new HashMap();

    //计算字符数
    public int ccount(File fileIn){
        int len = 0;
        try{
            //读取文件
            BufferedReader bf = new BufferedReader(new FileReader(fileIn));
            //给读取的字节码变量赋个初值-1
            int byte_char  = -1;
            //判断一行是否全为空格和“\r”组成，反正行数为有效行
            Boolean flag = false;
            //开始依次读取字节码
            while ((byte_char = bf.read()) >= 0 ){
                cnum++;
                //如果非空格和“\r\n”，当前行有效行标志flag变为true<
                if((byte_char != 10)&&(byte_char != 13)&&(byte_char != 32)&&(byte_char<128)){
                    flag = true;
                }
                //如果是空格或者“\r”
                else if ((byte_char == 10)||(byte_char == 32)){
                }
                //如果是“\n”
                else if(byte_char == 13){
                    cnum--;
                    if(flag){
                        lnum++;
                        flag = false;
                    }
                }else{
                    cnum--;
                    flag = true;
                }
                len = Count.judge(byte_char,len,ma);
            }
            //全都读取完，最后一个如果是符合要求的单词，随便输入一个非字母数字的字节码进行单词截断
            len = Count.judge(13,len,ma);
            bf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //以“\n”+flag判断有效行数会缺少第一行
        lnum++;
        return cnum;
    }

    //输出全局变量行数
    public int lcount(){
        return this.lnum;
    }


    //输出全局变量单词数
    public int wcount(){
        return this.wnum;
    }

    //单词排序
    public List<HashMap.Entry<String, Integer>> wordlist(){
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
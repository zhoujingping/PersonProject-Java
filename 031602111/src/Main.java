import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        //文件输入输出地址
        File fileIn = new File("D:/softTest/input.txt");
        String out_address= "D:/softTest/result.txt";
        FileOutputStream fileOut = new FileOutputStream(out_address);

        Count count = new Count();
        int cnum = count.ccount(fileIn);
        int lnum = count.lcount();
        int wnum = count.wcount();
        List<HashMap.Entry<String, Integer>>  wordList= count.wordlist();
        String result = "characters:" + cnum + "\r\n" +
                        "words:" + wnum      + "\r\n" +
                        "lines:" + lnum      + "\r\n" ;

        //输出单词
        int j = 0;
        String t = new String();
        if(wordList.size()!=0){
            for(;((j<10)&&(j<wordList.size()));j++){
                t = "<"+ wordList.get(j).getKey() + ">:" + wordList.get(j).getValue();
                result += t + "\r\n";
            }
        }
        fileOut.write(result.getBytes());
        fileOut.close();
    }
}

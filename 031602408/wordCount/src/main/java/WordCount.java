package main.java;
import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import lib.java.Print;  
public class WordCount {  
 
    public static void main(String[] args) throws Exception { 
    	Scanner scanner=new Scanner(System.in);
    	String pathname=scanner.nextLine();
        //将文本单词转化为小写
        Reader myReader = new FileReader(pathname);
    	Reader myBufferedReader = new BufferedReader(myReader);
    	CharArrayWriter  tempStream = new CharArrayWriter();  
    	int i = -1;
    	int k1=0;
     	do {
     		k1++;
    	if(i!=-1) 
    	tempStream.write(i);
    	i = myBufferedReader.read();
    	if(i >= 65 && i <= 90){
    	i += 32;
    	}
    	}while(i != -1);
    	myBufferedReader.close();
    	Writer myWriter = new FileWriter(pathname);
    	tempStream.writeTo(myWriter);
    	tempStream.flush();
    	tempStream.close();
    	myWriter.close();
    	///再次读取文档
    BufferedReader br = new BufferedReader(new FileReader(pathname));  
        
        List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表  
        String read= null;
		while((read = br.readLine()) != null){  
            String[] wordsArr1 = read.split("[^a-zA-Z]");  //过滤出只含有字母的  
            for (String word : wordsArr1) {  
                if(word.length() != 0){  //去除长度为0的行  
                	lists.add(word);  
                }  
            }  
        }  
          
        br.close();  
          
        Map<String, Integer> wordsCount = new TreeMap<String,Integer>();  //存储单词计数信息，key值为单词，value为单词数       
        for (String li : lists) {  
            if(wordsCount.get(li) != null){  
                wordsCount.put(li,wordsCount.get(li) + 1);  
            }else{  
                wordsCount.put(li,1);  
            }  
  
        }  
        System.out.println("Characters: "+k1);
        Print printwords=new Print();
        printwords.printWords(wordsCount);
        Print printline=new Print();
        printline.printline(pathname);
        Print printWordsFrequence=new Print();
        printWordsFrequence.printWordFrequence(wordsCount);
             }  
}
 
  

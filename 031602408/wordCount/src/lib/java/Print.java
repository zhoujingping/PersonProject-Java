package lib.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;  
public class Print {
	public static void printline(String pathname)
	{
		try
		{
	  BufferedReader br = new BufferedReader(new FileReader(pathname));  
	  
      int count=0;
      while (br.ready()) { 
          br.readLine(); 
          count ++;
      }
      System.out.println("lilnes: "+count);
         }
		catch(IOException e) { 
	            e.printStackTrace(); 
	        } 
}
	 public static void printWords( BufferedReader br){  
		 int wordcount = 0;
	        List<String> lists = new ArrayList<String>();  //存储过滤后单词的列表  
	        String readLine = null;
	        try
	        {
			while((readLine = br.readLine()) != null){  
				//wordline++;
	            String[] wordsArr1 = readLine.split("[^a-zA-Z0-9]");  //过滤出只含有字母的  
	            for (String word : wordsArr1) {  
	                if(word.length() != 0){  //去除长度为0的行
	                	
	                	while(!(word.charAt(0)>=97&&word.charAt(0)<=122))
	                	{
	                		word = word.substring(1, word.length());
	                	}
	                	if(word.length()>=4) wordcount++;
	                   // lists.add(word);  
	                }  
	            }  
	        }
		    System.out.println("words: "+wordcount);
	 }catch(IOException e) { 
         e.printStackTrace(); 
     } 
	    
	 }
	 public static void printWordFrequence(Map<String,Integer> oldmap){  
         
	        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());  
	          
	        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){    
	            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
	                return o2.getValue() - o1.getValue();  //降序  
	            }  
	        }); 
	        int k=0;
	        for(int i = 0; i<list.size(); i++){ 
	        	if(k>10)break;
	        	if(list.get(i).getKey().length()>3)
	        	{
	            System.out.println(list.get(i).getKey()+ ": " +list.get(i).getValue());  
	            k++;
	        }    
	   
	        }
	    } 
}

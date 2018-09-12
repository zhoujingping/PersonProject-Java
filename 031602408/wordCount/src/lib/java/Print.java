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
	 public static void printWordFrequence(Map<String,Integer> oldmap){  
         
	        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(oldmap.entrySet());  
	          
	        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){    
	            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {  
	                return o2.getValue() - o1.getValue();  //½µÐò  
	            }  
	        }); 
	        int k=0;
	        for(int i = 0; i<list.size(); i++){ 
	        	if(k>=10)break;
	        	if(list.get(i).getKey().length()>3)
	        	{
	            System.out.println("<"+list.get(i).getKey()+">"+ ": " +list.get(i).getValue());  
	            k++;
	        }    
	   
	        }
	    } 
}

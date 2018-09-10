


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static int characters_number=0;//字符数
    public static int words_number=0;//单词数
    public static int lines_number=0;//有效行数
    //相关参数设置为内部成员变量，算是偷懒；

     public static void main(String[] args) throws Exception{
	      @SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
	      String filepath=null;
	      if(args.length==0) {
	    	  
	      while(filepath==null) {
	    	  System.out.println("亲，请输入你要测试的文件：OTO");
	    	  filepath=input.next();
	      }
	     }
	      else
	      filepath=args[0];  
	      
	      File file = new File(filepath);
	      while(!file.exists()) {
	    	  System.out.println("亲，您输入的文件不存在，请重新输入：OTO");
	    	  String str=input.next();
	    	  filepath=str;
	    	  file = new File(filepath);
	      }
	     
	      StringBuffer buffer = new StringBuffer();
		  BufferedReader bf= new BufferedReader(new FileReader(filepath));
		  characters_count(file);
		  lines_count( bf, buffer); 
		  
	      String test1 = buffer.toString();//转换为String
	      String test=test1.toLowerCase();//大写字母转换为小写字母；
		  Map<String,Integer> map=new HashMap<String,Integer>();//构建哈希表
		  Matcher t=Pattern.compile("[^a-z0-9]+([a-z]{4}[a-z-9]*)").matcher(" "+test);
		  //正则表达式匹配单词，空格防止第一个单词如果前面没有符号没有被匹配。
	      words_count(map,t,words_number);//单词个数计数
	      ArrayList<Entry<String,Integer>> list
	         =new ArrayList<Entry<String,Integer>>(map.entrySet());
	         listSort(map,list);//排序
	         Output(list); 
	         System.out.println("亲，py交易成功 ORZ");
     }
     //********
  public static void characters_count(File file) { 
    	 
	  Reader reader = null; 
	  try {  
              int s;
		    reader = new InputStreamReader(new FileInputStream(file));  
		              while ((s=reader.read()) != -1) { 
		            	  if((char)s=='\n')
		            		  continue;//因为windows中换行是“\r\n",reader.read读到换行会读成两个字符；
		                  characters_number++;
		             }  

	              reader.close();  

		          } catch (Exception e) {  

		             e.printStackTrace();  
		             }  
    	
     }//字符数统计；
     public static void lines_count(BufferedReader bf,StringBuffer buffer) throws IOException {
    	 
         int num=0;String str;
         while((str = bf.readLine())!=null){//使用readLine方法，一次读一行,readLine不能读出换行,换行符=总行数-1；
	            buffer.append(' '+str);//readLine会去掉换行，如果不加空格，上一行的末尾单词和下一行的开头单词会连在一起；
	            if(!str.matches("\\s*"))
	    	         num++;
	        }//匹配有效行数；//buffer 用来统计单词个数；
    	 lines_number=num;
     }//行数统计；
     
     public static void words_count(Map<String,Integer> map,Matcher t,int num){
          String str;
	     for(;t.find();num++) {
	    	 str=t.group(1);
			if(map.containsKey(str)){
				map.put(str, map.get(str)+1);
			}//如果存在键值则+1
			else {
				map.put(str, 1);//不存在就放入键值
			}
			
	    }
	     words_number=num;
    }//单词个数统计；

    //**********
     public static void listSort(Map<String,Integer> map,ArrayList<Entry<String,Integer>> list){
	        
 	     Comparator<Entry<String, Integer>> c=new Comparator<Entry<String,Integer>>()
 	     {
 		      public int compare(Entry<String,Integer>i,Entry<String,Integer>j)
 		      {
 	          int k=j.getValue().compareTo(i.getValue());
 	          if(k==0)return -j.getKey().compareTo(i.getKey());
 	           return k;
 		      }
 	     };
 	     Collections.sort(list,c);//使用Collections的sort方法，并且重写compare方法
	}//降序排序list；

     public static void Output(ArrayList<Entry<String,Integer>> list) throws IOException {
    	 PrintWriter out=new PrintWriter(new FileOutputStream("result.txt"));
         out.println("characters: "+characters_number);
         out.println("words: "+words_number);
         out.println("lines: "+lines_number);
	     for(int num=0;num<Math.min(list.size(),10);num++) {
    	 out.println("<"+list.get(num).getKey()+">:"+list.get(num).getValue());//输出
    	 }
	     out.close();
     }//输出到result.txt文件中，可能需要具体到某条路径，不然我也不清楚那个文件在哪里


}

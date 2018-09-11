


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
	public static int characters_number=0;//�ַ���
    public static int words_number=0;//������
    public static int lines_number=0;//��Ч����
    //��ز�������Ϊ�ڲ���Ա����������͵����

     public static void main(String[] args) throws Exception{
	      @SuppressWarnings("resource")
		Scanner input=new Scanner(System.in);
	      String filepath=null;
	      if(args.length==0) {
	    	  
	      while(filepath==null) {
	    	  System.out.println("�ף���������Ҫ���Ե��ļ���OTO");
	    	  filepath=input.next();
	      }
	     }
	      else
	      filepath=args[0];  
	      
	      File file = new File(filepath);
	      while(!file.exists()) {
	    	  System.out.println("�ף���������ļ������ڣ����������룺OTO");
	    	  String str=input.next();
	    	  filepath=str;
	    	  file = new File(filepath);
	      }
	     
	      StringBuffer buffer = new StringBuffer();
		  BufferedReader bf= new BufferedReader(new FileReader(filepath));
		  characters_count(file);
		  lines_count( bf, buffer); 
		  
	      String test1 = buffer.toString();//ת��ΪString
	      String test=test1.toLowerCase();//��д��ĸת��ΪСд��ĸ��
		  Map<String,Integer> map=new HashMap<String,Integer>();//������ϣ��
		  Matcher t=Pattern.compile("[^a-z0-9]+([a-z]{4}[a-z-9]*)").matcher(" "+test);
		  //������ʽƥ�䵥�ʣ��ո��ֹ��һ���������ǰ��û�з���û�б�ƥ�䡣
	      words_count(map,t,words_number);//���ʸ�������
	      ArrayList<Entry<String,Integer>> list
	         =new ArrayList<Entry<String,Integer>>(map.entrySet());
	         listSort(map,list);//����
	         Output(list); 
	         System.out.println("�ף�py���׳ɹ� ORZ");
     }
     //********
  public static void characters_count(File file) { 
    	 
	  Reader reader = null; 
	  try {  
              int s;
		    reader = new InputStreamReader(new FileInputStream(file));  
		              while ((s=reader.read()) != -1) { 
		            	  if((char)s=='\n')
		            		  continue;//��Ϊwindows�л����ǡ�\r\n",reader.read�������л���������ַ���
		                  characters_number++;
		             }  

	              reader.close();  

		          } catch (Exception e) {  

		             e.printStackTrace();  
		             }  
    	
     }//�ַ���ͳ�ƣ�
     public static void lines_count(BufferedReader bf,StringBuffer buffer) throws IOException {
    	 
         int num=0;String str;
         while((str = bf.readLine())!=null){//ʹ��readLine������һ�ζ�һ��,readLine���ܶ�������,���з�=������-1��
	            buffer.append(' '+str);//readLine��ȥ�����У�������ӿո���һ�е�ĩβ���ʺ���һ�еĿ�ͷ���ʻ�����һ��
	            if(!str.matches("\\s*"))
	    	         num++;
	        }//ƥ����Ч������//buffer ����ͳ�Ƶ��ʸ�����
    	 lines_number=num;
     }//����ͳ�ƣ�
     
     public static void words_count(Map<String,Integer> map,Matcher t,int num){
          String str;
	     for(;t.find();num++) {
	    	 str=t.group(1);
			if(map.containsKey(str)){
				map.put(str, map.get(str)+1);
			}//������ڼ�ֵ��+1
			else {
				map.put(str, 1);//�����ھͷ����ֵ
			}
			
	    }
	     words_number=num;
    }//���ʸ���ͳ�ƣ�

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
 	     Collections.sort(list,c);//ʹ��Collections��sort������������дcompare����
	}//��������list��

     public static void Output(ArrayList<Entry<String,Integer>> list) throws IOException {
    	 PrintWriter out=new PrintWriter(new FileOutputStream("result.txt"));
         out.println("characters: "+characters_number);
         out.println("words: "+words_number);
         out.println("lines: "+lines_number);
	     for(int num=0;num<Math.min(list.size(),10);num++) {
    	 out.println("<"+list.get(num).getKey()+">:"+list.get(num).getValue());//���
    	 }
	     out.close();
     }//�����result.txt�ļ��У�������Ҫ���嵽ĳ��·������Ȼ��Ҳ������Ǹ��ļ�������


}

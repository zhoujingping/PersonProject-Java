package workOut;

import java.io.*;
import java.util.*;
import java.util.regex.*;


public class Main {

	public static void main(String[] args)throws IOException {
		//读入input.txt文件
		
		File f = new File(args[0]);
        Scanner input = new Scanner(f);
        String path = input.next();
        InputStreamReader reader = new InputStreamReader(new FileInputStream(args[0])); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); 
		 String str = null;
		 int lines = 0;
		 int characters = 0;
		 int words = 0;
		 Pattern pattern = Pattern.compile("[a-zA-Z]{4,}[a-zA-Z0-9]*");
		 Matcher matcher;
		 String word = null;
		 HashMap<String,Integer> hashMap = new HashMap<String,Integer>();//用于储存单词及其个数
		 while((str = br.readLine())!=null)
		 {
			 characters+=str.length();//计算字符数
			 lines++;//计算行数
			 matcher = pattern.matcher(str);
			 while(matcher.find()) {
				 words++; //计算单词总数
				 word = matcher.group();
				 if(hashMap.containsKey(word)) {
					 hashMap.put(word, hashMap.get(word)+1);//计算单词出现次数
				 }else {
					 hashMap.put(word, 1);
				 }
			 }
		 }
		 reader.close();
		 
		 //写入result.txt文件
		 
		 File file = new File("result.txt");
		 file.createNewFile();
		 FileOutputStream fos = new  FileOutputStream(file);
		 PrintStream ps = new PrintStream(fos);
		 System.setOut(ps);
		 System.out.println("characters: "+characters);
		 System.out.println("words: "+words);
		 System.out.println("lines: "+lines);
		 sortWords(hashMap);
		 
	}
	
	
	
	//hashMap中按照value和key排序
	public static void sortWords(HashMap<String,Integer>hashMap){
		 ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
		 Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
			 public int compare(Map.Entry<String, Integer>e1,Map.Entry<String, Integer>e2) {
				 int re =e2.getValue().toString().compareTo(e1.getValue().toString());
				 if(re!=0) {
					 return re;
				 }
				 else {
					 return e2.getKey().toString().compareTo(e1.getKey());
				 }	 
			 }
		 });
		 for(int i=0;i<10;i++) {
			 
			 System.out.println(list.get(i).getKey().toLowerCase()+": "+list.get(i).getValue());
		 }
	 }


}

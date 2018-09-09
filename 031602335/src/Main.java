

import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<String> strArray = new ArrayList<String> ();//存储每一行的字符串
	static ArrayList<String> wordArray2 = new ArrayList<String> ();//存储分割后的字符串
	static int lines = 0;// 行数
	static int characters = 0;// 字符数
	static int words = 0;// 单词数
	static HashMap<String, Integer> hashMap = new HashMap<String, Integer>();// 用于储存单词及其个数
	static ArrayList<Map.Entry<String, Integer>> list; // 用于储存entry

	public static void main(String[] args) throws IOException {

		Main m = new Main();
		m.readFile(args);
		m.workOut();
		m.sortWords();
		m.writeFile();

	}

	// 读入文件

	public void readFile(String[] args) throws IOException {

		if (args != null && args.length > 0) {

			InputStreamReader reader = new InputStreamReader(new FileInputStream(args[0]));
			BufferedReader br = new BufferedReader(reader);
			String str = null;

			while ((str = br.readLine()) != null) {
				String[] wordArray = str.split("\\s*[^a-zA-Z0-9]+");
				for(String word :wordArray) {
					wordArray2.add(word);
				}
				strArray.add(str);
			}
			
			reader.close();
		}
	}

	// 实现计算字符数、行数、单词总数，出现次数

	public void workOut() {

		
		
		for (int i =0;i<strArray.size();i++) {
			Main.characters += Main.strArray.get(i).length();// 计算字符数
			Main.lines++;// 计算行数
			
		}
		for(String word : wordArray2) {
			if(word.matches("[a-zA-Z]{4,}[a-zA-Z0-9]*")) {
				Main.words++; // 计算单词总数
				word = word.toLowerCase();
				if (Main.hashMap.containsKey(word)) {
					Main.hashMap.put(word, hashMap.get(word) + 1);// 计算单词出现次数
				} else {
					Main.hashMap.put(word, 1);
				}
				
			}
			
			
		}
		Main.list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
	}

	// hashMap中按照value的降序和key的升序排序

	public void sortWords() {
		Collections.sort(Main.list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
				int re = e2.getValue().toString().compareTo(e1.getValue().toString());
				return (re == 0) ? e1.getKey().compareTo(e2.getKey()) : re;

			}

		});

	}

	// 写入文件

	public void writeFile() throws IOException {

		File f = new File("result.txt");
		f.createNewFile();
		FileOutputStream fos = new FileOutputStream(f);
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
		System.out.println("characters: " + characters);
		System.out.println("words: " + words);
		System.out.println("lines: " + lines);

		for (int i = 0; i < 10 && i < list.size(); i++) {

			System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
		}

	}

}

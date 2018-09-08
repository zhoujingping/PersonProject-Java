package workOut;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Main {

	static ArrayList<String> strArray = new ArrayList<String>();
	static int lines = 0;//行数
	static int characters = 0;//字符数
	static int words = 0;//单词数
	static HashMap<String, Integer> hashMap = new HashMap<String, Integer>();// 用于储存单词及其个数
	static ArrayList<Map.Entry<String, Integer>> list; //用于排序

	public static void main(String[] args) throws IOException {

		readFile(args);
		workOut();
		writeFile();

	}

	// hashMap中按照value的降序和key的升序排序
	
	public static void sortWords(HashMap<String, Integer> hashMap, ArrayList<Map.Entry<String, Integer>> list) {
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
				int re = e2.getValue().toString().compareTo(e1.getValue().toString());
				if (re != 0) {
					return re;
				} else {
					return e2.getKey().toString().compareTo(e1.getKey());
				}
			}
		});

	}

	// 读入文件
	
	public static void readFile(String[] args) throws IOException {

		InputStreamReader reader = new InputStreamReader(new FileInputStream(args[0]));
		BufferedReader br = new BufferedReader(reader);
		String str = null;

		while ((str = br.readLine()) != null) {
			strArray.add(str);
		}
		reader.close();
	}

	// 实现计算字符数、行数、单词总数，出现次数

	public static void workOut() {

		Pattern pattern = Pattern.compile("[a-zA-Z]{4,}[a-zA-Z0-9]*");
		Matcher matcher;
		String word = null;
		for (int i = 0; i < strArray.size(); i++) {
			characters += strArray.get(i).length();// 计算字符数
			lines++;// 计算行数
			matcher = pattern.matcher(strArray.get(i));
			while (matcher.find()) {
				words++; // 计算单词总数
				word = matcher.group();
				if (hashMap.containsKey(word)) {
					hashMap.put(word, hashMap.get(word) + 1);// 计算单词出现次数
				} else {
					hashMap.put(word, 1);
				}
			}
		}
	}

	// 写入文件
	
	public static void writeFile() throws IOException {

		File f = new File("result.txt");
		f.createNewFile();
		FileOutputStream fos = new FileOutputStream(f);
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
		System.out.println("characters: " + characters);
		System.out.println("words: " + words);
		System.out.println("lines: " + lines);
		list = new ArrayList<Map.Entry<String, Integer>>(hashMap.entrySet());
		sortWords(hashMap, list);
		for (int i = 0; i < 10; i++) {

			System.out.println(list.get(i).getKey().toLowerCase() + ": " + list.get(i).getValue());
		}
	}

}

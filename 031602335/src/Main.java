package workOut;

import java.io.*;
import java.util.*;

public class Main {

	
	private static int lines = 0;// 行数
	private static int characters = 0;// 字符数
	private static int words = 0;// 单词数
	private static ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>();

	public static void main(String[] args) throws FileNotFoundException, IOException  {

		Main m = new Main();
		File f = m.readFile(args);
		
		Main.characters = Lib.countChars(f);
		Main.lines = Lib.countLines(f);
		Main.words = Lib.countWords(f);
		Main.list = Lib.countTop10(f);

		m.writeFile();


	}

	// 读入文件

	public File readFile(String[] args) {

		String fileName = null;
		File f = null;
		
		if(args.length == 0 || args == null) {
			Scanner scan = new Scanner(System.in);
			fileName = scan.nextLine();
			scan.close();
		}
		else {
			fileName = args[0];
		}
		
		f = new File(fileName);
		if(!f.exists()) {
			System.out.println(f+"文件不存在");
			System.exit(0);
		}
		
		return f;
		
	}



	// 写入文件

	public void writeFile()  {

		File f = new File("result.txt");
		FileOutputStream fos = null;
		try {
			f.createNewFile();
			fos = new FileOutputStream(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

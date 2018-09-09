import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = null;
		if (0 == args.length) {
			Scanner scan = new Scanner(System.in);
			fileName = scan.nextLine();
			scan.close();
		} else {
			fileName = args[0];
		}
		File file = new File(fileName);
		// 为了减少文件读写，先将文本内容存到string中
		String txt = readInfo(file);
		int charCount = lib.charCount(txt);
		int lineCount = lib.lineCount(txt);
		int wordCount = lib.wordCount(txt);
		List<Entry<String, Integer>> wordCountTopTen = lib.wordCountTopTen(txt);
		/*
		 * 第二种方法 int charCount = lib.charCount(file); 
		 * int lineCount=lib.lineCount(file); 
		 * int wordCount = lib.wordCount(file);
		 * List<Entry<String,Integer>> wordCountTopTen = lib.wordCountTopTen(file);
		 */
		writeInfo(charCount, wordCount, lineCount, wordCountTopTen);
		printInfo();
	}

	/*
	 * 从文件读取行数
	 */
	public static String readInfo(File file) {
		BufferedReader reader = null;
		String string = null;
		StringBuffer stringbuffer = null;
		boolean isFirstLine = true;
		try {
			reader = new BufferedReader(new FileReader(file));
			stringbuffer = new StringBuffer("");
			while ((string = reader.readLine()) != null) {
				if (isFirstLine)
					stringbuffer.append(string);
				else {
					stringbuffer.append("\r\n" + string);
				}
				isFirstLine = false;
			}
			reader.close();
			return stringbuffer.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void writeInfo(int charCount, int wordCount, int lineCount,
			List<Entry<String, Integer>> wordCountTopTen) {
		BufferedWriter writer = null;
		File file = new File("result.txt");
		try {
			if (file.exists() && file.isFile()) {
				file.delete();
			}
			file.createNewFile();

			writer = new BufferedWriter(new FileWriter(file, true));
			// 写入字符个数
			writer.write("characters: " + String.valueOf(charCount));

			writer.newLine();
			// 写入单词个数
			writer.write("words: " + String.valueOf(wordCount));

			writer.newLine();
			// 写入行数
			writer.write("lines: " + String.valueOf(lineCount));

			int topTen = 10;
			for (Map.Entry<String, Integer> entry : wordCountTopTen) {
				writer.newLine();
				writer.write("<" + entry.getKey() + ">: " + entry.getValue());
				topTen--;
				if (topTen == 0) {
					break;
				}
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public static void printInfo() {
		File file = new File("Result.txt");
		BufferedReader reader = null;
		String string = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((string = reader.readLine()) != null) {
				System.out.println(string);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		}
	}
}

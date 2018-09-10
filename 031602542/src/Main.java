import java.io.BufferedWriter;
import java.io.File;
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
		if (!file.exists()) {
			System.out.println(file+"文件不存在");
			System.exit(0);
		}
		if (!file.isFile()) {
			System.out.println("文件错误");
			System.exit(0);
		}
		if (fileName != null) {
			int charCount = lib.charCount(file);
			int lineCount = lib.lineCount(file);
			int wordCount = lib.wordCount(file);
			List<Entry<String, Integer>> wordCountTopTen = lib.wordCountTopTen(file);
			writeInfo(charCount, wordCount, lineCount, wordCountTopTen);
		}
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
			System.out.println("characters: " + String.valueOf(charCount));

			writer.newLine();
			// 写入单词个数
			writer.write("words: " + String.valueOf(wordCount));
			System.out.println("words: " + String.valueOf(wordCount));

			writer.newLine();
			// 写入行数
			writer.write("lines: " + String.valueOf(lineCount));
			System.out.println("lines: " + String.valueOf(lineCount));

			for (Map.Entry<String, Integer> entry : wordCountTopTen) {
				writer.newLine();
				writer.write("<" + entry.getKey() + ">: " + entry.getValue());
				System.out.println("<" + entry.getKey() + ">: " + entry.getValue());
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}

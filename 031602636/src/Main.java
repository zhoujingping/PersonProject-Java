import java.io.File;
import java.util.Scanner;
/*
 * 主程序，可以用命令行接受参数
 */
public class Main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String path = scanner.nextLine();
		File file = new File(path);
		int charcount = charCount.charCount(file);
		System.out.println("characters:" + charCount.charCount(file));
		System.out.println("words:" + wordCount.wordCount(file));
		System.out.println("lines:" + lineCount.lineCount(file));
		
		writeInTxt.writeInTxt("characters:" + charCount.charCount(file));
		writeInTxt.writeInTxt("words:" + wordCount.wordCount(file));
		writeInTxt.writeInTxt("lines:" + lineCount.lineCount(file));
		wordDetail.wordDetail(file);
	}
}

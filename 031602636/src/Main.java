import java.io.File;
import java.util.Scanner;
/*
 * 主程序，可以用命令行接受参数
 */
public class Main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String path = scanner.nextLine();
		
		System.out.println(path);
		File file = new File(path);
		int charcount = charCount.charCount(file);
		System.out.println(charcount);
	}
}

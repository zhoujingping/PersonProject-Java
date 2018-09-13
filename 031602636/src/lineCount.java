
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;

public class lineCount {
	public static int linecount(File file) {
		int linecount = 0;
		try {
			if (file.isFile() && file.exists()) {	//检测文件名是否正常或文件是否存在
				FileReader fileReader = new FileReader(file);
				LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
				while(lineNumberReader.readLine() != null) {
					linecount++;
				}
				lineNumberReader.close();
			} else {
				System.out.println("文件不存在");
			}
		}catch (Exception e) {
			System.out.println("文件读取错误");
		}
		return linecount;
	}
}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class charCount {

	public static int charCount(File file) {
		int charcount = 0;
		try {
			if (file.isFile() && file.exists()) {	//检测文件名是否正常或文件是否存在
				InputStreamReader isReader = new InputStreamReader(new FileInputStream(file),"utf-8");
				BufferedReader bReader = new BufferedReader(isReader);
				while(bReader.read() != -1)
					charcount++;
				bReader.close();
			} else {
				System.out.println("文件不存在");
			}
		}catch (Exception e) {
			System.out.println("文件读取错误");
		}
		return charcount;
	}
	
}

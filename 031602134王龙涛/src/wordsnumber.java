import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class wordsnumber implements Core {
	private int count=0;
	private File file1;
	private File file2;
	public void Mycount(File file3,File file4) {
		file1=file3;
		file2=file4;
		try {
		        //读取文件内容
				InputStream input=null;
				input=new FileInputStream(file1);
				byte b[]=new byte[(int)file1.length()];
				for(int i=0;i<b.length;i++)
				{
					b[i]=(byte)input.read();
				}
				input.close();
				//使用正则式对读取的字符串进行分割
				String regex="[\\s\\p{Punct}]+";
				String str=new String(b);
				String words[]=str.split(regex);
				//判断是否是符合标准的单词，若是则加1
				for(int i=0;i<words.length;i++)
				{
					if(words[i].length()<4)
						continue;
					boolean judge=true;
					for(int j=0;j<=3;j++)
					{
						if(words[i].charAt(j)<'A'||words[i].charAt(j)>'z')
							judge=false;
					}
					if(judge)
					{
						count++;
					}
				}
				String wordstring="words: "+String.valueOf(count)+"\r\n";
				FileWriter out=new FileWriter(file2,true);
				out.write(wordstring);
				out.close();
			}catch(IOException e) {
				System.out.println(e);
		}
	}
}

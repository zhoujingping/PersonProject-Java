import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class charactersnumber implements Core{
	private int count=0;
	private File file1;
	private File file2;
	public void Mycount(File file3,File file4)
	{
		file1=file3;
		file2=file4;
		try
		{
		    //读取文件中的内容
			FileReader inone=new FileReader(file1);
			BufferedReader buf=new BufferedReader(inone);
			String str=null;
			//按行读取
			while((str=buf.readLine())!=null)
			{
				count++;
				for(int i=0;i<str.length();i++)
				{
					if((int)str.charAt(i)>=0&&(int)str.charAt(i)<128)//若是ASICC表中的字符，则加1
					{
						count++;
					}
				}
			}
			buf.close();
			String characters="charactersnumber: "+String.valueOf(count)+"\r\n";
			FileWriter out=new FileWriter(file2,true);
			out.write(characters);
			out.close();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
}
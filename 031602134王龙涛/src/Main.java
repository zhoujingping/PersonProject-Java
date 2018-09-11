import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void lines(String s)
	{
		BufferedReader buf=null;
		try
		{
			FileReader inone=new FileReader(s);
			buf=new BufferedReader(inone);
			String str=null;
			int count=0;
			while((str=buf.readLine())!=null)
			{
				for(int i=0;i<str.length();i++)
				{
					if(str.charAt(i)!=' ')
					{
						count++;
						break;
					}
				}
			}
			String writestring="lines: "+String.valueOf(count)+"\r\n";
			File file=new File("result.txt");
			FileWriter out=new FileWriter(file,true);
			out.write(writestring);
			out.close();
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
	}
	public static void main(String[] args) throws Exception{
		Scanner in=new Scanner(System.in);
		String s=in.nextLine();
		in.close();
		File file1=new File(s);
		File file2=new File("result.txt");
		charactersnumber count1;
		wordsnumber count2;
		flequentwords count3;
		count1=new charactersnumber();
		count2=new wordsnumber();
		count3=new flequentwords();
		count1.Mycount(file1, file2);
		count2.Mycount(file1, file2);
		lines(s);
		count3.Mycount(file1, file2);
	}
}
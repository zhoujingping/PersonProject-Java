import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class flequentwords implements Core {
    //重写排序方法
	private static class ValueComparator implements Comparator<Map.Entry<String,Integer>>
	{
		public int compare(Map.Entry<String,Integer> m,Map.Entry<String,Integer> n)
		{
			return n.getValue()-m.getValue();
		}
	}
	private File file1;
	private File file2;
	public void Mycount(File file3,File file4) {
		file1=file3;
		file2=file4;
		try {
				Map<String,Integer> map=new HashMap<>();
				//读取文件内容
				InputStream input=null;
				input=new FileInputStream(file1);
				byte b[]=new byte[(int)file1.length()];
				for(int i=0;i<b.length;i++)
				{
					b[i]=(byte)input.read();
				}
				input.close();
				String str=new String(b);
				//分割读取的字符串
				String regex="[\\s\\p{Punct}]+";
				String words[]=str.split(regex);
				//判断分割的字符串是否是单词
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
					//如果是单词，就判断是否已经再HashMap之中
					if(judge)
					{
						if(map.containsKey(words[i]))//如果再HashMap之中，则将其频率，也就是value加1
						{
							map.put(words[i], map.get(words[i])+1);
						}
						else//若是不再HashMap之中，则加入到其中，并且value为1
						{
							map.put(words[i], 1);
						}
					}
				}
				//将HashMap转换为List
				List<Map.Entry<String,Integer>> list=new ArrayList<>();
				list.addAll(map.entrySet());
				//将list按照value值进行排序
				ValueComparator vc=new ValueComparator();
				Collections.sort(list,vc);
				Entry<String, Integer> te=list.get(0);
				int count=te.getValue();
				String[] change=new String[list.size()];
				int[] intchange=new int[list.size()];
				//将排序之后的list放入数组，一个是字符串数组，一个是整型数组
				for(int i=0;i<list.size();i++)
				{
					te=list.get(i);
					change[i]=te.getKey().toLowerCase();
					intchange[i]=te.getValue();
				}
				//将频率相同的字符串进行字典排序
				for(int i=1,jet=0;i<list.size()&&i<10;i++)
				{
					te=list.get(i);
					if(count!=te.getValue()||i==9||i==list.size()-1)
					{
						if(i==9||i==list.size()-1) i++;
						String[] change2=new String[i-jet];
						for(int j=jet,k=0;j<i;j++,k++)
						{
							change2[k]=change[j];
						}
						Arrays.sort(change2);
						for(int j=jet,k=0;j<i;j++,k++)
						{
							change[j]=change2[k];
						}
						jet=i;
					}
					count=te.getValue();
				}
				FileWriter out=new FileWriter(file2,true);
				for(int i=0;i<list.size()&&i<10;i++)
				{
					String result="<"+change[i]+">: "+String.valueOf(intchange[i])+"\r\n";
					if(i==list.size()-1||i==9) 
						result="<"+change[i]+">: "+String.valueOf(intchange[i]);
					out.write(result);
					
				}
				out.close();
		}catch(IOException e) {
			System.out.println(e);
		}
	}
}
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class wordDetail {
	
	public static void wordDetail(File file) {
		String buffer;
		Map<String,Integer> map=new HashMap<String,Integer>();
		try {
			BufferedReader reader=new BufferedReader(new FileReader(file));
			while((buffer=reader.readLine())!=null){
				StringTokenizer st = new StringTokenizer(buffer, ", !’-#()[]?%*<>");
				while(st.hasMoreTokens()){
					String str=st.nextToken();
					if(map.containsKey(str)) 
						map.put(str, map.get(str)+1);
					else map.put(str, 1);
				}
			}
			Set<WordEntity> set=new TreeSet<WordEntity>();
			for(String s:map.keySet()){
				WordEntity wordEntry=new WordEntity(s,map.get(s));
				set.add(wordEntry);     
			}
			Iterator<WordEntity>  ite=set.iterator();
			int count=0;
			while(ite.hasNext()){
				if(count>=10) 
					break;
				System.out.println(ite.next());
				count++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("文件没有找到");
		}
		catch(IOException e){
			System.out.println("文件读异常");    
		}
	}
}

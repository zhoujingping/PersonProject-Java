/*
import com.siberia.demo.MapValueComparator;
*/

import java.io.*;
import java.util.*;


public class lib {
    /*统计字符数*/
    public static int countChar(String filePath){
        int characters=0;
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                int totalLine=0;
                /*读取文件数据*/
                StringBuffer sb=null;
                BufferedReader br1;
                try{
                    br1=new BufferedReader(new FileReader(file));
                    String temp=br1.readLine();
                    sb=new StringBuffer();
                    while(temp!=null){
                        sb.append(temp);
                        /*统计总行数*/
                        totalLine++;
                        temp=br1.readLine();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                /*读取的内容*/
                String info=sb.toString();
                /*统计字符个数*/
                characters=info.length()+totalLine-1;
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return characters;
    }
    /*统计单词数*/
    public static int countWord(String pathname) {
        StringBuilder content = new StringBuilder("");
        int words = 0;
        try {
            String encoding = "UTF-8";
            File file = new File(pathname);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                /*读取文件数据*/
                StringBuffer sb = null;
                BufferedReader br1;
                try {
                    br1 = new BufferedReader(new FileReader(file));
                    String temp = br1.readLine();
                    sb = new StringBuffer();
                    while (temp != null) {
                        sb.append(temp);
                        sb.append(" ");//每行结束多读一个空格
                        temp = br1.readLine();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String info = sb.toString();
                String s[] = info.split(",|\\.| |\\?|\\!|\\'|\t");
                /*统计单词个数*/
                Map<String,String> map = new HashMap<String,String>();
                for (int i = 0; i < s.length; i++) {
                    if (s[i].length() >= 4) {
                        String temp = s[i].substring(0, 4);
                        temp = temp.replaceAll("[^a-zA-Z]", "");

                        if (temp.length() >= 4) {
                            words++;
                        }
                    }
                }
            }
        }catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return words;
    }
    /*统计最多的十个单词及其词频*/
    public static Map<String,String> countFrequency(String pathname){
        StringBuilder content = new StringBuilder("");
        Map<String,String> map = new HashMap<String,String>();
        try {
            String encoding = "UTF-8";
            File file = new File(pathname);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                /*读取文件数据*/
                StringBuffer sb=null;
                BufferedReader br1;
                try{
                    br1=new BufferedReader(new FileReader(file));
                    String temp=br1.readLine();
                    sb=new StringBuffer();
                    while(temp!=null){
                        sb.append(temp);
                        sb.append(" ");//每行结束多读一个空格
                        temp=br1.readLine();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                /*读取的内容*/
                String info=sb.toString();
                String s[]=info.split(",|\\.| |\\?|\\!|\\'|\t");
                /*统计单词个数*/
                for(int i=0;i<s.length;i++){
                    if(s[i].length()>=4) {
                        String temp = s[i].substring(0, 4);
                        temp=temp.replaceAll("[^a-zA-Z]", "");

                        if (temp.length() >= 4) {
                            if (map.containsKey(s[i].toLowerCase())) {//判断Map集合对象中是否包含指定的键名
                                map.put(s[i].toLowerCase(), Integer.parseInt(map.get(s[i].toLowerCase())) + 1 + "");

                            } else {
                                map.put(s[i].toLowerCase(), 1 + "");

                            }
                        }
                    }
                }
                /*map排序*/
                map=sortMapByValue(map);
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return map;
    }
    /*统计行数*/
    public static int countLines(String pathname) {
        int lines = 0;
        StringBuilder content = new StringBuilder("");
        try {
            String encoding = "UTF-8";
            File file = new File(pathname);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader br1;
                try {
                    br1 = new BufferedReader(new FileReader(file));
                    String temp = br1.readLine();
                    while (temp != null) {
                        /*统计有效行数*/
                        if (!temp.isEmpty()) {
                            lines++;
                        }
                        temp = br1.readLine();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return lines;

    }
    /*生成新txt*/
    public static void printFile(int characters, int words, int lines, Map<String, String> map) {
        StringBuilder content = new StringBuilder("");
        int count = 0;
        while (count != 3) {
            switch (count) {
                case 0:
                    content.append("characters:");
                    content.append(characters);
                    break;
                case 1:
                    content.append("words:");
                    content.append(words);
                    break;
                case 2:
                    content.append("lines:");
                    content.append(lines);
                    break;
            }
            count++;
            content.append("\r\n");
        }
        Set<String> keys = map.keySet();
        count = 1;
        for (String key : keys) {
            content.append("<" + key + ">:" + map.get(key));
            count++;
            if (count > 10)
                break;
            content.append("\r\n");
        }
        System.out.println(content);
        BufferedWriter bw = null;
        try {
            String pathname = "./result.txt";
            File file = new File(pathname);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(content.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*map用value排序*/
    public static Map<String, String> sortMapByValue(Map<String, String> oriMap) {
        if (oriMap == null || oriMap.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
                oriMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());

        Iterator<Map.Entry<String, String>> iter = entryList.iterator();
        Map.Entry<String, String> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
}

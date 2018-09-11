package com.eventide.wordCount.service;

import java.io.*;

/**
 * 字符计数器，包括计算文件内总字符数（ASCII码）.
 *
 * @author xyy
 * @version 1.0 2018/9/11
 * @since 2018/9/11
 */
public class CharCounter {
    /**
     * 读取并计算文件内总字符数（ASCII码）.
     *
     * @param fileName 文件名
     * @return 总字符数
     */
    public static long countChar(String fileName) {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        String in = null;
        long charNum = 0;

        try {
            inputStreamReader = new InputStreamReader(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("找不到此文件");
            e.printStackTrace();
        }
        if (inputStreamReader != null) {
            bufferedReader = new BufferedReader(inputStreamReader);
        }
        try {
            while ((in = bufferedReader.readLine()) != null) {
                charNum += in.length();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return charNum;
    }
}

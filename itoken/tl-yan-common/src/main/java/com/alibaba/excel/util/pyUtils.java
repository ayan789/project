package com.alibaba.excel.util;

import java.io.*;

public class pyUtils {
    /**
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
//      Process proc;
        try {
            String abc = "It is normal for the jaguar to be lazy in this environment Tiger is the king of forest but you simply put some small animals around him That is why the jaguar shows no interest in going out Why don 't you put two wolves or at least a jackal around him";
            String[] args1 = new String[] { "python", "/Users/yanshuai/Desktop/yd.py", new String(abc.getBytes(),"UTF-8")};
            Process proc = Runtime.getRuntime().exec(args1);// 执行py文件
//          proc = Runtime.getRuntime().exec("python /Users/yanshuai/Desktop/yd.py  Liberation Army");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(),"UTF-8"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

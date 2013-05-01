package commonTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Author: gnoloahs
 * Date: 2013-04-19
 * Time: 下午1:40
 */
public class StartTest
{

    public static void main(String[] args) throws Exception
    {
        // TODO Auto-generated method stub
        Process process = Runtime.getRuntime().exec("c:\\stop.bat"); // 调用外部程序
        final InputStream in = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder buf = new StringBuilder();
        String line = null;

        while ((line = br.readLine()) != null) buf.append(line);
        System.out.println("输出结果为：" + buf);

//  new Thread(new Runnable() {
//      public void run() {
//          BufferedReader br = new BufferedReader(new InputStreamReader(in));
//          try {
//     while(br.readLine() != null) ;
//    } catch (IOException e) {
//     // TODO Auto-generated catch block
//     e.printStackTrace();
//    }
//      }
//  }).start();// 启动单独的线程来清空process.getInputStream()的缓冲区
//  InputStream error = process.getErrorStream();
//  BufferedReader br = new BufferedReader(new InputStreamReader(error));
//  StringBuilder buf = new StringBuilder(); // 保存输出结果流
//  String line = null;
//  while((line = br.readLine()) != null) buf.append(line); // 循环等待ffmpeg进程结束
//  System.out.println("输出结果为：" + buf);
    }


}

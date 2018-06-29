package tcp.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class clientTextDemo {

	public static void main(String[] args) throws Exception {

		Socket s=new Socket("127.0.0.1",10004);
		
		//键盘录入
		BufferedReader bufr=new BufferedReader(new InputStreamReader(System.in));
		
		//socket输出流
//		BufferedWriter bufw=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		PrintWriter out=new PrintWriter(s.getOutputStream(),true);//true表示刷新，将数据刷入
		
		//得到从服务端得到过来的数据
		BufferedReader bufIn=new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		String line=null;
		while((line=bufr.readLine())!=null){//阻塞式方法：读取到结束标识才结束，不然一直进行、等待
			if("over".equals(line)){
				break;
			}
			out.println(line);
			
			//out.print(line+"\r\n");
			//out.flush();//刷新，将数据刷入
			
			//读取从服务端得到过来的大写数据
			String uppStr=bufIn.readLine();
			System.out.println(uppStr);
			
		}
		
		s.close();
		
		
		
		
		
		
	}

}

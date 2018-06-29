package tcp.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class serverTextDemo {

	public static void main(String[] args) throws Exception {

		ServerSocket ss=new ServerSocket(10004);
		
		Socket s=ss.accept();
		
		String ip=s.getInetAddress().getHostAddress();
		System.out.println(ip+":");
		BufferedReader bufr=new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		//获取输出流，并装饰
		PrintWriter out=new PrintWriter(s.getOutputStream(),true);
		
		String lineText=null;
		while((lineText=bufr.readLine())!=null){
			System.out.println(lineText);//从客户端得到的数据
			
			out.println(lineText.toUpperCase());//将从客户端得到的数据再重新发到客户端
			
		}
		
		s.close();
		ss.close();
	}

}

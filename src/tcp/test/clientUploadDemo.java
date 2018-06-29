package tcp.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class clientUploadDemo {

	public static void main(String[] args) throws Exception {
//		uploadTextFile();
		uploadImage();
		
	}
	
	
	public static void uploadTextFile() throws Exception{
		Socket s=new Socket("127.0.0.1",10004);
		
		//读取文件
		BufferedReader bufr=new BufferedReader(new FileReader("client.txt"));
		
		//socket输出流
//		BufferedWriter bufw=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		PrintWriter out=new PrintWriter(s.getOutputStream(),true);//true表示刷新，将数据刷入
		
		//得到从服务端得到过来的数据
		BufferedReader bufIn=new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		String line=null;
		while((line=bufr.readLine())!=null){//阻塞式方法：读取到结束标识才结束，不然一直进行、等待
//			if("over".equals(line)){
//				break;
//			}
			out.println(line);
			
			//out.print(line+"\r\n");
			//out.flush();//刷新，将数据刷入
			
			//读取从服务端得到过来的数据
			
		}
		//告诉服务端写完了
		s.shutdownOutput();
//		out.println("!@#$%^%$#@!");
		
		String text=bufIn.readLine();
		System.out.println(text);
		
		s.close();
	}
	
	
	public static void uploadImage() throws Exception{
		
		
		Socket s=new Socket("127.0.0.1",10005);
		
		//读取图片文件
		FileInputStream fileIn=new FileInputStream("12345.jpg");
		
		OutputStream out=s.getOutputStream();
		
		byte[] buf=new byte[1024];
		int len=0;
		
		while((len=fileIn.read(buf))!=-1){
			out.write(buf, 0, len);
		}
		//告诉服务端这边数据发送完成，让服务端停止读取
		s.shutdownOutput();
		
		InputStream in=s.getInputStream();//获取从服务端的的字节信息
		byte[] bufIn=new byte[1024];
		int lenIn=in.read(bufIn);
		System.out.println(new String(bufIn, 0, lenIn));
		
		
		
//		if(fileIn!=null)
		fileIn.close();
		s.close();
	}

}

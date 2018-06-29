package tcp.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class serverUploadDemo {

	public static void main(String[] args) throws Exception {
//		uploadTextFile();
		uploadImage();
		
	}
	
	
	public static void uploadTextFile() throws Exception{
		ServerSocket ss=new ServerSocket(10004);
		
		Socket s=ss.accept();
		
		String ip=s.getInetAddress().getHostAddress();
		System.out.println(ip+":");
		BufferedReader bufIn=new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		BufferedWriter bufw=new BufferedWriter(new FileWriter("C:/Users/Shawn/Desktop/新建文件夹/server.txt"));
		
		//获取输出流，并装饰
		PrintWriter out=new PrintWriter(s.getOutputStream(),true);
		
		String lineText=null;
		while((lineText=bufIn.readLine())!=null){
			System.out.println(lineText);//从客户端得到的数据
			
//			if("!@#$%^%$#@!".equals(lineText)){
//				break;
//			}
			
			bufw.write(lineText);
			bufw.newLine();
			bufw.flush();
			
		}

		bufw.close();
		
		out.println("上传成功");//将提示信息发到客户端
		
		s.close();
		ss.close();
	}
	
	
	public static void uploadImage() throws Exception{
		ServerSocket ss=new ServerSocket(10005);
		
		while(true){
			Socket s=ss.accept();
			
			String ip=s.getInetAddress().getHostAddress();
			System.out.println(ip+":");
			
		
			// 读取客户端发来的数据。
			InputStream in=s.getInputStream();
			
		
			// 将读取到数据存储到一个文件中。
	//		File dir = new File("C:/Users/92809/Desktop/新建文件夹");
	//		if (!dir.exists()) {
	//			dir.mkdirs();
	//		}
	//		File file = new File(ip + ".png");
	//		//如果文件已经存在于服务端 
	//		while(file.exists()){
	//			file = new File(dir,ip+".jpg");
	//		}
			FileOutputStream fos=new FileOutputStream("C:/Users/Shawn/Desktop/新建文件夹/12345.jpg");
			byte[] buf=new byte[1024];
			int len=0;
			while((len=in.read(buf))!=-1){
	//			System.out.println(new String(buf,0,len));
				fos.write(buf,0,len);
			}
			
			fos.close();
			
			OutputStream out=s.getOutputStream();
			out.write("上传成功".getBytes());//将提示信息发到客户端
			
			s.close();
//			ss.close();
		}
	}

}

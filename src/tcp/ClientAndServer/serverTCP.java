package tcp.ClientAndServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class serverTCP {

	public static void main(String[] args) throws Exception {
		while(true){
		ServerSocket ss=new ServerSocket(8888);
	
		Socket s=ss.accept();
			
		
		String ip=s.getInetAddress().getHostAddress();
		
		InputStream in=s.getInputStream();
		
		byte[] buf=new byte[1024];
		int len=in.read(buf);
		String text=new String(buf, 0, len);
		System.out.println("在服务端显示的客户端内容："+ip+text);
		
		OutputStream out=s.getOutputStream();
		out.write("客户端：我收到了。。。".getBytes());
		
		s.close();
		ss.close();
		}
		
	}

}

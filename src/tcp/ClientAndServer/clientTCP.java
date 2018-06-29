package tcp.ClientAndServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class clientTCP {

	public static void main(String[] args) throws Exception {

		Socket s=new Socket("127.0.0.1",8888);
		String ip=s.getInetAddress().getHostName();
		
		OutputStream out=s.getOutputStream();
		out.write("客户端：我来了。。。".getBytes());
		
		InputStream in=s.getInputStream();
		byte[] buf=new byte[1024];
		int len=in.read(buf);
		System.out.println("从服务端收到的信息："+ip+new String(buf, 0, len));
		
		s.close();
	}

}

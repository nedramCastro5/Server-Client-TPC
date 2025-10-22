package ti;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		
		try {
			System.out.println("Waiting For Clients...");
			ServerSocket ss = new ServerSocket(9806);
			Socket soc = ss.accept();
			BufferedReader in = new BufferedReader
					(new InputStreamReader(soc.getInputStream()));
			String str = in.readLine();
			
			System.out.println(str);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.getStackTrace();
		}
	}
}

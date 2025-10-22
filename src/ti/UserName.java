package ti;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UserName {
	private String userNome;
	private String file = "src/user.txt";
	
	public String getUserNome() {
		return userNome;
	}

	public void setUserNome(String userNome) {
		this.userNome = userNome;
	}
	
	public void saveName(String name) {
		try{
			FileWriter writer = new FileWriter(file);
			PrintWriter pw = new PrintWriter(writer);
			pw.print(name);	
			
			pw.close();
			writer.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	
	public String getUser() {

		String record = null;
		String name = null;
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			record = new String();
			
			while((record = reader.readLine()) != null) {
				name = record;
			}
			
			reader.close();
			
			
		}catch(IOException e) {
			e.getStackTrace();
		}
		

		return name;
	}

}

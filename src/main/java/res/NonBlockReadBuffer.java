package res;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NonBlockReadBuffer{

	String res = null;
	boolean inputFlag;
	
	public NonBlockReadBuffer() {
		// TODO Auto-generated constructor stub
	}
	
	public String getLine() throws IOException{
		inputFlag = true;
		
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader r = new BufferedReader(is);
		
		while(System.in.available() != 0){
			System.in.skip(System.in.available());
		}
		
		String res = null;
		inputFlag = true;
		try {
			while(inputFlag){
				if(r.ready()){
					res = r.readLine();	
					inputFlag = false;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} 

		return res;
	}
	
	public void setInputFlagtoTrue(){
		inputFlag = true;
	};
	
	public void setInputFlagtoFalse(){
		inputFlag = false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

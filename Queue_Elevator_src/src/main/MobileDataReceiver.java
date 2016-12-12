package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MobileDataReceiver extends Thread {

	private TopManager myTop;
	Socket mySock;
	private BufferedReader in;

	public MobileDataReceiver(TopManager _myTop) {
		myTop = _myTop;
		try{
			mySock = new Socket("110.9.105.26", 9999);
			in = new BufferedReader(new InputStreamReader(
					mySock.getInputStream()));
		}catch(IOException e){}
	}

	@Override
	public void run() {
		String msg = null;

		try {
			while (true) {
				msg = in.readLine();
				if (msg != null) {
					System.out.println(msg);
					String[] values = msg.split(":");
					
					String id = null;
					int[] valuesInt = new int[3];
					int i = 0;
					
					boolean first = true;
					for (String str : values){
						if(first == true){
							id = str;
							first = false;
							
						}
						else{
						valuesInt[i] = Integer.parseInt(str);
						i++;
						}
					}

					myTop.addReq(id,valuesInt[0], valuesInt[1], valuesInt[2]);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (mySock != null) {
					mySock.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.interrupt();
		}
	}
}

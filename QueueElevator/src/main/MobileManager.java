package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MobileManager implements Runnable {
	
	public static final int PORT = 9999;
	private ServerSocket serverSock;
	
	private TopManager myTopM;
	
	public MobileManager(TopManager _topM){
		myTopM = _topM;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			System.out.println("S: Preparing...");
			serverSock = new ServerSocket(PORT);
			System.out.println("S: Running...");
			
			Socket clnt = serverSock.accept();
			while(true){
				System.out.println("S: Receiving...");
				
				try{
					BufferedReader in = new BufferedReader(new InputStreamReader(clnt.getInputStream()));
					String data = in.readLine();
					System.out.println("S: Data recieved - "+data);
					
					if(data == null){
						System.out.println("NULL RECV");
						break;
					}
					
					PrintWriter out = new PrintWriter(clnt.getOutputStream(), true);
					if(makeRequest(data)){
						System.out.println("S: Received!");
						out.println("RECV");
					}else{
						System.out.println("S: Wrong!");
						out.println("WRONG");
					}
					
				}catch(Exception e){
					System.out.println("S: Error");
					e.printStackTrace();
				}finally{
					System.out.println("S: Done");
				}
			}
		}catch(Exception e){
			System.out.println("S: Error");
			e.printStackTrace();
		}
	}
	
	private boolean makeRequest(String data){
		String name;
		int depart, arrive, delay;
		String[] arr = data.split(":"); //data = Dept:Arrive
		depart = Integer.parseInt(arr[0]);
		arrive = Integer.parseInt(arr[1]);
		delay = Integer.parseInt(arr[2]);
		name = arr[3];
		
		if(isBounds(depart) && isBounds(arrive) && arr.length == 2){
			//TopManager method
			myTopM.getRequestFromMobile(name, depart, arrive, delay);
			return true;
		}else{
			return false;
		}
	}
	public boolean isBounds(int n){
		if(n < 1 || n > 7){
			return false;
		}else{
			return true;
		}
	}
}
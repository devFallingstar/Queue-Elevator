package main;

public class Request {
	String id;
	int time; //sec
	//indicating floor
	int depart;
	int arrive;
	
	public Request(String id, int time,int depart,int arrive){
		this.id = id;
		this.time = time;
		this.depart = depart;
		this.arrive = arrive;
	}
	public String getId(){return id;}
	public int getTime(){return time;}
	public int getDepart(){return depart;}
	public int getArrive(){return arrive;}
}

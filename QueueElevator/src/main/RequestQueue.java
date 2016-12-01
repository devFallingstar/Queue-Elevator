package main;

public class RequestQueue {
	final static int MAX = 500;
	int rear;
	int front;
	Request rq[] = new Request[MAX];

	public RequestQueue(){
		this.rear = -1;
		this.front = -1;
	}

	public void put(Request r){
		rq[++front] = r;
	}

	public Request get(){
		if(front == rear){
			Request empty = new Request("",-1,-1,-1);
			return empty;
		}
		return rq[++rear];
	}

	public int getNextReqTime(){
		if(front == rear)return -1;

		return rq[rear+1].time;
	}
	
	public int getReuqestNumber(){
		return front+1;
	}



}

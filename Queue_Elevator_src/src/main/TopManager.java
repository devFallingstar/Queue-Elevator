package main;

import javax.swing.JLabel;

public class TopManager extends Thread{
	private LPacmanManager lpm;
	private RPacmanManager rpm;

	private RequestQueue lpmRq;
	private RequestQueue rpmRq;

	private static RequestQueue rq;

	int mode;

	private JLabel wt;
	public TopManager(int mode, JLabel wt,LPacmanManager lpm, RPacmanManager rpm){
		this.lpm = lpm;
		this.rpm = rpm;
		this.lpmRq = lpm.rq;
		this.rpmRq = rpm.rq;
		this.mode = mode;
		rq = new RequestQueue();

		this.wt = wt;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(mode == 1){//finite
			preparedRequest();
			requestDistributor();
			
			while(true){
				System.out.flush();
				requestDistributorSingle();
				
				wt.setText(""+(lpm.respTime + rpm.respTime));
				if(lpm.end == true && rpm.end == true){

					new WaitTimeAnimation(wt).start();
					break;
				}
			}
		}else{
			MobileDataReceiver myMobile = new MobileDataReceiver(this);
			myMobile.start();
			
			while(true){
				System.out.flush();
				requestDistributorSingle();
				
				wt.setText(""+(lpm.respTime + rpm.respTime));
				if(lpm.end == true && rpm.end == true){

					new WaitTimeAnimation(wt).start();
					break;
				}
			}
		}
	}

	private void requestDistributorSingle(){
		Request request = rq.get();
		if(request != null){
			int value = request.depart - request.arrive;
			if(value > 0){//down
				lpmRq.put(request);
			}
			else if(value < 0){//up
				rpmRq.put(request);
			}
		}
	}
	
	private void requestDistributor(){
		while(true){
			Request request = rq.get();
			if(request.arrive == -1)break;//empty
			int value = request.depart - request.arrive;
			if(value > 0){//down
				lpmRq.put(request);
			}
			else if(value < 0){//up
				rpmRq.put(request);
			}
		}
	}

	private void preparedRequest(){
		rq.put(new Request("MIKLE",6,4,1));
		rq.put(new Request("CABIN",8,2,5));
		rq.put(new Request("ROMAN",10,4,1));
		rq.put(new Request("BEATY",14,3,1));
		rq.put(new Request("SOLU",20,0,4));	
		rq.put(new Request("NARI",21,2,5));
		rq.put(new Request("GONSI",24,0,6));
		rq.put(new Request("CAFE",27,1,2));
		rq.put(new Request("ORING",32,0,3));
		rq.put(new Request("MIKLE",35,4,1));
		rq.put(new Request("CABIN",40,2,5));
		rq.put(new Request("ROMAN",42,4,1));
		rq.put(new Request("BEATY",44,0,4));
		rq.put(new Request("SOLU",46,5,1));	
		rq.put(new Request("NARI",47,6,1));
		rq.put(new Request("GONSI",50,0,3));
		rq.put(new Request("CAFE",53,2,1));
		rq.put(new Request("ORING",56,2,5));
		rq.put(new Request("MIKLE",57,4,1));
		rq.put(new Request("CABIN",60,2,5));
		rq.put(new Request("ROMAN",61,3,0));
		rq.put(new Request("BEATY",62,2,5));
		rq.put(new Request("SOLU",64,5,1));	
		rq.put(new Request("NARI",65,2,3));
		rq.put(new Request("GONSI",70,4,1));
		rq.put(new Request("CAFE",72,2,5));
		rq.put(new Request("ORING",78,4,6));
		rq.put(new Request("ORING",82,0,6));
		rq.put(new Request("ORING",84,1,2));
		rq.put(new Request("ORING",90,2,4));
	}
	public void addReq(String id, int dept, int arrive, int delay){
		rq.put(new Request(id, rpm.timer+delay, dept, arrive));
	}
}

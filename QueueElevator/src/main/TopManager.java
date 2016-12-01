package main;

import javax.swing.JLabel;

public class TopManager extends Thread{
	private LPacmanManager lpm;
	private RPacmanManager rpm;

	private RequestQueue lpmRq;
	private RequestQueue rpmRq;

	private RequestQueue rq;

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
		}else{
			//Thread run
		}
		
		reqeustDistributor();
		
		while(true){
			System.out.flush();
			//here, add infinite version code.
			
			wt.setText(""+(lpm.respTime + rpm.respTime));
			if(lpm.end == true && rpm.end == true){

				new WaitTimeAnimation(wt).start();
				break;
			}
		}
	}

	private void reqeustDistributor(){
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
	
	public void getRequestFromMobile(String _name, int _depart, int _arrive, int delay){
		Request newRq = new Request(_name, rpm.getTimer()+delay ,_depart+1, _arrive+1);
		
		rq.put(newRq);
	}

	private void preparedRequest(){
		
		rq.put(new Request("MIKLE",6,4,1));
		rq.put(new Request("CABIN",6,4,1));
		rq.put(new Request("ROMAN",6,4,1));
		rq.put(new Request("BEATY",6,3,1));
		rq.put(new Request("SOLU",6,5,1));	
		rq.put(new Request("NARI",6,6,1));
		rq.put(new Request("GONSI",14,4,1));
		rq.put(new Request("CAFE",16,2,1));
		rq.put(new Request("ORING",16,5,2));
//
//		rq.put(new Request("",6,4,1));
//		rq.put(new Request("",6,4,1));
//		rq.put(new Request("",6,4,1));
//		rq.put(new Request("",6,3,1));
//		rq.put(new Request("",6,5,1));
//		rq.put(new Request("",6,6,1));
//		rq.put(new Request("",14,4,1));
//		rq.put(new Request("",16,2,1));
//		rq.put(new Request("",16,5,2));
//		rq.put(new Request("",20,5,2));
//		rq.put(new Request("",21,4,1));
//		rq.put(new Request("",21,4,1));
//		rq.put(new Request("",21,4,1));
//		rq.put(new Request("",22,3,1));
//		rq.put(new Request("",22,5,1));
//		rq.put(new Request("",24,6,1));
//		rq.put(new Request("",24,6,3));
//		rq.put(new Request("",25,5,3));
//		rq.put(new Request("",25,4,2));
//
//		rq.put(new Request("",14,4,1));
//		rq.put(new Request("",16,2,1));
//		rq.put(new Request("",16,5,2));
//		rq.put(new Request("",20,5,2));
//		rq.put(new Request("",21,4,1));
//		rq.put(new Request("",21,4,1));
//		rq.put(new Request("",21,4,1));
//		rq.put(new Request("",22,3,1));
//		rq.put(new Request("",22,5,1));
//		rq.put(new Request("",24,6,1));
//		rq.put(new Request("",24,6,3));
//		rq.put(new Request("",25,5,3));
//		rq.put(new Request("",25,4,2));
//		rq.put(new Request("",25,5,1));
//		rq.put(new Request("",25,4,2));
//		rq.put(new Request("",26,2,1));
//		rq.put(new Request("",26,5,2));
//		rq.put(new Request("",27,5,2));
//		rq.put(new Request("",30,6,3));
//		rq.put(new Request("",30,5,3));
//		rq.put(new Request("",30,4,2));
//		rq.put(new Request("",31,5,1));
//		rq.put(new Request("",32,3,0));
//		rq.put(new Request("",33,1,0));
//		rq.put(new Request("",33,4,0));
//		rq.put(new Request("",34,2,1));
//
		rq.put(new Request("MIKLE",6,2,4));
		rq.put(new Request("CABIN",6,1,4));
		rq.put(new Request("ROMAN",6,1,4));
		rq.put(new Request("BEATY",6,1,4));
		rq.put(new Request("SOLU",6,1,4));
		rq.put(new Request("NARI",6,1,4));
		rq.put(new Request("GONSI",14,1,4));
		rq.put(new Request("CAFE",16,1,2));
		rq.put(new Request("ORING",50,2,5));

	}


}

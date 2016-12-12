package main;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import util.SetIconToLabel;

public class RPacmanManager extends Thread{
	static final int ELEV_NUM = 3;
	RPacman pms[] = new RPacman[ELEV_NUM];
	int index = 0;
	RArriveList arriveList[] = new RArriveList[ELEV_NUM];
	int respTime = 0;
	RequestQueue rq;
	ArriveBank arriveBank;//Acumulate Arrive Board
	FloorState floorState;
	public int timer;
	int crashOffset[] = new int[3];
	Main parent;
	JButton callBtn[];
	JLabel RFloorLb[];
	int critical = 0;
	boolean end;
	int mode;
	public RPacmanManager(Main parent, int mode){
		this.mode = mode;
		this.parent = parent;
		callBtn = parent.rElevBtn;
		RFloorLb = parent.RFloorLb;
		this.timer = 0;
		this.end = false;
		floorState = new FloorState();
		arriveList[0] = new RArriveList();
		arriveList[1] = new RArriveList();
		arriveList[2] = new RArriveList();
		crashOffset[0] = 0;
		crashOffset[1] = 0;
		crashOffset[2] = 0;

		arriveBank = new ArriveBank();
		rq = new RequestQueue();
		
//		if(mode == 1)
//			preparedRequest(); 

	}
	

	
	private void preparedRequest(){
//		rq.put(new Request("MIKLE",6,2,4));
//		rq.put(new Request("CABIN",6,1,4));
//		rq.put(new Request("ROMAN",6,1,4));
//		rq.put(new Request("BEATY",6,1,4));
//		rq.put(new Request("SOLU",6,1,4));
//		rq.put(new Request("NARI",6,1,4));
//		rq.put(new Request("GONSI",14,1,4));
//		rq.put(new Request("CAFE",16,1,2));
//		rq.put(new Request("ORING",16,2,5));

		
	}

	public int sectionToFloor(int sec){
		if(sec == 2 || sec == 14) return 0;
		else if(sec == 3 || sec == 15) return 1;
		else if(sec == 4 || sec == 16) return 2;
		else if(sec == 5 || sec == 17) return 3;
		else if(sec == 6 || sec == 18) return 4;
		else if(sec == 7|| sec == 19) return 5;
		if(sec == 8|| sec == 20) return 6;
		return  -1;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			sleep(1000);
		}catch(Exception e){}
			
		while(true){
			SynchronizePms();
			if(RArriveAnimation.total == rq.getReuqestNumber() && mode == 1){
//				System.out.println("respTime : " + respTime);
				end = true;
				break;
			}

		}
	}
	private void enterElevZone(){
		int biggestIdx = -1;
		int biggestSct = -1;
		int smallestIdx = -1;
		int smallestSct = 9;
		for(int i = 0; i < 3; i++){

			int sct = pms[i].currentSct;

			if(sct >= 0 && sct < 9){
				if(biggestSct < sct){
					biggestSct = sct;
					biggestIdx = i;
				}
				if(smallestSct > sct){
					smallestSct = sct;
					smallestIdx = i;
				}
			}
		}
		//find mid
		int midIdx = -1;
		for(int i = 0; i < 3; i++){
			if(biggestIdx != i && smallestIdx != i){
				midIdx = i;
				break;
			}
		}

		for(int i = 6 ; i >= 0; i--){
			if(biggestIdx != -1)
				if(floorState.getFloorState(i) == 1 || ((arriveList[biggestIdx].getFloorState(i) > 0) && floorState.getFloorState(i) == 0)){

					if(pms[biggestIdx].preFloor != i && biggestIdx != -1 && pms[biggestIdx].stopBy == false &&
							i == sectionToFloor(pms[biggestIdx].currentSct)){
						pms[biggestIdx].stopBy = true;
						floorState.setFloorProcessing(i);
						for(int k = 0 ; k < 7; k++){
							if(i == k && arriveList[biggestIdx].getFloorState(i) != 0){
								for(int j = 0; j < arriveList[biggestIdx].getFloorState(i); j++){
									RArriveAnimation ani = new RArriveAnimation(parent,i,j);
									ani.start();
								}
							}
							arriveList[biggestIdx].setFloorActive(k,arriveBank.getActiveState(i,k));
						}
						arriveBank.setAllInActive(i);
						continue;
					}
				}

			if(midIdx != -1)
				if(floorState.getFloorState(i) == 1|| ((arriveList[midIdx].getFloorState(i) > 0) && floorState.getFloorState(i) == 0)){
					if(pms[midIdx].preFloor != i && midIdx != -1 && pms[midIdx].stopBy == false &&
							i == sectionToFloor(pms[midIdx].currentSct)){
						pms[midIdx].stopBy = true;
						floorState.setFloorProcessing(i);
						for(int k = 0 ; k < 7; k++){
							if(i == k && arriveList[midIdx].getFloorState(i) != 0){
								for(int j = 0; j < arriveList[midIdx].getFloorState(i); j++){
									RArriveAnimation ani = new RArriveAnimation(parent,i,j);
									ani.start();
								}

							}
							arriveList[midIdx].setFloorActive(k,arriveBank.getActiveState(i,k));
						}
						arriveBank.setAllInActive(i);
						continue;
					}
				}
			if(smallestIdx != -1)
				if(floorState.getFloorState(i) == 1||((arriveList[smallestIdx].getFloorState(i) > 0) && floorState.getFloorState(i) == 0)){
					if(pms[smallestIdx].preFloor != i && smallestIdx != -1 && pms[smallestIdx].stopBy == false &&
							i == sectionToFloor(pms[smallestIdx].currentSct)){
						pms[smallestIdx].stopBy = true;
						floorState.setFloorProcessing(i);
						for(int k = 0 ; k < 7; k++){
							if(i == k && arriveList[smallestIdx].getFloorState(i) != 0){

								for(int j = 0; j < arriveList[smallestIdx].getFloorState(i); j++){
									RArriveAnimation ani = new RArriveAnimation(parent,i,j);
									ani.start();
								}

							}
							arriveList[smallestIdx].setFloorActive(k,arriveBank.getActiveState(i,k));
						}
						arriveBank.setAllInActive(i);
						continue;
					}
				}
		}

	}

	private void outElevZone(){

		int cc1 = 2, cc2 = -2;

		int cnt = 0;
		for(int i = 0 ; i < 3; i++){
			if(pms[i].stop == true){
				for(int k = 0 ; k < 7; k++){
					arriveList[i].setFloorActive(k,arriveBank.getActiveState(sectionToFloor(pms[i].currentSct-12),k));
				}
				arriveBank.setAllInActive(sectionToFloor(pms[i].currentSct-12));
				RFloorLb[sectionToFloor(pms[i].currentSct-12)].setIcon(null);

				int d1 = (pms[i].currentSct-12) - pms[(i+1)%3].currentSct;
				if(d1 >= cc1 || d1 <= cc2){
					int k = i-1;
					if(k < 0)k = 2;
					int d2 = (pms[i].currentSct-12) - pms[k].currentSct;
					if(d2 >= cc1 || d2 <= cc2){
						int nxt = arriveList[i].getNextArriveFloor(sectionToFloor(pms[i].currentSct-12));
						if(nxt != -1){
							if(floorState.getFloorState(nxt) != 0){
								cnt++;
								continue;
							}
							int j;
							j = (i+1)%3;
							if(pms[i].currentSct < pms[j].currentSct &&
									arriveList[j].getNextArriveFloor(sectionToFloor(pms[j].currentSct-12)) == nxt){
								cnt++;
							
								continue;
							}
							j = i-1;
							if(i-1 < 0)
								j = 2;
							if(pms[i].currentSct < pms[j].currentSct &&
									arriveList[j].getNextArriveFloor(sectionToFloor(pms[j].currentSct-12)) == nxt){
								cnt++;
							
								continue;
							}

						}


						pms[i].canBack = true;
						floorState.setFloorInActive(sectionToFloor(pms[i].currentSct-12));
						arriveList[i].setFloorInActive(sectionToFloor(pms[i].currentSct-12));
						ImageIcon icon = new ImageIcon("img/struct/up_0.png");
						callBtn[sectionToFloor(pms[i].currentSct-12)].setIcon(icon);
						cnt=0;
					}
				}

			}
		}

		//		stuck
		if(cnt == 3){
			try{
			
				//find most front
				int mostIdx = 0;
				int mostSct = pms[0].currentSct;
				for(int p = 1; p < 3; p++){
					if(mostSct < pms[p].currentSct){
						mostSct = pms[p].currentSct;
						mostIdx = p;
					}
				}
				pms[mostIdx].canBack = true;

				floorState.setFloorInActive(sectionToFloor(pms[mostIdx].currentSct-12));
				arriveList[mostIdx].setFloorInActive(sectionToFloor(pms[mostIdx].currentSct-12));
				ImageIcon icon = new ImageIcon("img/struct/up_0.png");
				callBtn[sectionToFloor(pms[mostIdx].currentSct-12)].setIcon(icon);
			}catch(Exception e){}
		}
	}

	private void crashDefense(){
		for(int i = 0 ; i < ELEV_NUM; i++){ 
			if(pms[i].stop == false){
				if(arriveList[(i+1)%3].getNextArriveFloor(sectionToFloor(pms[(i+1)%3].currentSct)) ==
						arriveList[i].getNextArriveFloor(sectionToFloor(pms[i].currentSct+1))){
					if(arriveList[i].getNextArriveFloor(sectionToFloor(pms[i].currentSct)) != -1)
						if(sectionToFloor(pms[(i+1)%3].currentSct) == sectionToFloor((pms[i].currentSct+1))){
							System.out.println("stop");
							crashOffset[i] = 2;
							pms[i].loadStop = true;
							continue;
						}
				}
				int t = i-1;
				if(t < 0) t = 2;
				else if(arriveList[t].getNextArriveFloor(sectionToFloor(pms[t].currentSct)) ==
						arriveList[i].getNextArriveFloor(sectionToFloor(pms[i].currentSct+1))){
					if(arriveList[i].getNextArriveFloor(sectionToFloor(pms[i].currentSct)) != -1)
						if(sectionToFloor(pms[t].currentSct) == sectionToFloor((pms[i].currentSct+1))){
							System.out.println("stop");
							crashOffset[i] = 2;
							pms[i].loadStop = true;
							continue;
						}
				}
				else if(pms[(i+1)%3].stop == true && sectionToFloor(pms[(i+1)%3].currentSct-12) ==
						arriveList[i].getNextArriveFloor(sectionToFloor(pms[i].currentSct+1))){
					pms[i].loadStop = true;
					crashOffset[i] = 1;
				
					continue;
				}
				else if(pms[t].stop == true && sectionToFloor(pms[t].currentSct-12) ==
						arriveList[i].getNextArriveFloor(sectionToFloor(pms[i].currentSct+1))){
					pms[i].loadStop = true;
					crashOffset[i] = 1;
				
					continue;
				}
				if(crashOffset[i] != 0){
					crashOffset[i]--;
					continue;
				}


				pms[i].loadStop = false;

			}
		}

		if( (pms[0].loadStop || pms[0].stop) && (pms[1].loadStop || pms[1].stop) && (pms[2].loadStop || pms[2].stop)){
	
			//find most front
			int mostIdx = -1;
			int mostSct = -1;
			for(int p = 0; p < 3; p++){
				if(pms[p].stop == true)
					if(mostSct < pms[p].currentSct){
						mostSct = pms[p].currentSct;
						mostIdx = p;
					}
			}
			pms[mostIdx].canBack = true;
			pms[mostIdx].loadStop = false;
			floorState.setFloorInActive(sectionToFloor(pms[mostIdx].currentSct-12));
			arriveList[mostIdx].setFloorInActive(sectionToFloor(pms[mostIdx].currentSct-12));
			ImageIcon icon = new ImageIcon("img/struct/up_0.png");
			callBtn[sectionToFloor(pms[mostIdx].currentSct-12)].setIcon(icon);
		}
	}
	private void responseTime(){
		for(int i = 0; i < 7; i++){
			for(int k = 0 ; k < 7; k++){
				int state = arriveBank.getActiveState(i,k);
				if(state > 0){
					for(int j = 0 ; j < state; j++)
						respTime++;
				}
			}
		}
	}

	private void SynchronizePms(){
		System.out.flush();
		if(pms[0].canMove == false && pms[1].canMove == false &&
				pms[2].canMove == false){
			responseTime();
			checkRequest();
			enterElevZone();
			outElevZone();
			crashDefense();
			pms[0].canMove = true;
			pms[1].canMove = true;
			pms[2].canMove = true;
			timer++;
			
			parent.setTitle("[ Time : "+timer + " ]");

		}
	}

	private void checkRequest(){
		Request tmp;
		int t;
		while(true){
			t = rq.getNextReqTime();
			if(t == -1)break;
			else if(t != timer)break;
			else if(t == timer){
				tmp = rq.get();
				t = tmp.getTime();
				getCall(tmp.depart);
				arriveBank.setActive(tmp.depart, tmp.arrive);
				parent.notice.setText(tmp.id + " " + tmp.getTime()+ "T" + " [" +(tmp.depart+1) + "F -> " + (tmp.arrive+1) + "F]");
			}
		}
	}

	public int getCall(int floor){//0~6 floor
		if(floorState.getFloorState(floor) == 0){
			floorState.setFloorActive(floor);
			ImageIcon icon = new ImageIcon("img/struct/up_1.png");
			callBtn[floor].setIcon(icon);
			SetIconToLabel.set(RFloorLb[floor],"img/ghost1.png",55,55);
			return 0;
		}
		return -1;
	}

	public void addPacman(RPacman pm){
		pm.start();
		pms[index++] = pm;
	}
	
	public void setEnd(boolean b){
		this.end = b;
	}

}

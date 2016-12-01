package main;

public class LArriveList {
	int floor[] = new int[7];
	//0: inactive
	//1~: active
	public LArriveList(){
		for(int i = 0 ; i < floor.length; i++){
			floor[i] = 0;
		}
	}
	
	public int getFloorState(int n){
		return floor[n];
	}
	
	public void setFloorActive(int f, int n){
		floor[f] = floor[f] + n;
	}
	
	public void setFloorInActive(int n){
		floor[n] = 0;
	}
	
	public int getNextArriveFloor(int f){//f : current floor
		for(int i = f-1; i >= 0; i--){
			if(floor[i] > 0)return i;
		}
		return -1;//
	}
	
	public int getArriveFloor(){
		for(int i = 6; i >= 0; i--){
			if(floor[i] > 0)return i;
		}
		return -1;//
	}
}

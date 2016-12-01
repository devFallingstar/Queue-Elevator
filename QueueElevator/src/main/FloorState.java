package main;

public class FloorState {
	int floor[] = new int[7];
	//-1: processing
	//0: inactive
	//1: active
	
	public FloorState(){
		for(int i = 0 ; i < floor.length; i++){
			floor[i] = 0;
		}
	}
	
	public int getFloorState(int n){
		return floor[n];
	}
	
	public void setFloorActive(int n){
		floor[n] = 1;
	}
	
	public void setFloorInActive(int n){
		floor[n] = 0;
	}
	public void setFloorProcessing(int n){
		floor[n] = -1;
	}
}

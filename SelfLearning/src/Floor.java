
public class Floor {
	private int floorNo;
	private CustomTime[][] timePart;
	
	public Floor(int _floorNo){
		this.floorNo = _floorNo;
		setTimePart();
	}
	
	private void setTimePart(){
		int i, j;
		
		this.timePart = new CustomTime[7][24];
		
		for (i = 0; i < 7; i++){
			for(j = 0; j < 24; j++){
				timePart[i][j] = new CustomTime(i, j+"", "00");
			}
		}
	}
	
	public void pressed(int _day, int _hour){
		CustomTime currentTime = timePart[_day][_hour];
		currentTime.addPressNum();
	}
	
	public CustomTime getTimePart(int _day, int _hour){
		return timePart[_day][_hour];
	}
	
}

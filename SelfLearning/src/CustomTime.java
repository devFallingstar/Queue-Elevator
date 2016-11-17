public class CustomTime {
	int day;
	String hour, minute;
	int pressed;
	
	public CustomTime(int _day, String _hour, String _min){
		this.day = _day;
		if(_hour.length() == 1){
			_hour = "0"+_hour;
		}
		this.hour = _hour;
		this.minute = _min;
		
		pressed = 0;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}
	
	public int getPressedNum(){
		return pressed;
	}
	
	public void addPressNum(){
		this.pressed += 1;
	}
	
	
}

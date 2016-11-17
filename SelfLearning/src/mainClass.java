public class mainClass {
	private String[] dayName = {"일", "월", "화", "수", "목", "금", "토"};
	private static DataProcess dataP = new DataProcess(7);
	
	public static void main(String[] args){
		//버튼 누르는거 추가.
		//월요일
		press(5, 1, 9);
		press(3, 1, 10);
		press(4, 1, 14);
		
		//화요일
		press(5, 2, 9);
		press(3, 2, 10);
		press(4, 2, 10);
		press(4, 2, 16);
		
		//수요일
		press(3, 3, 10);
		press(5, 3, 16);
		
		//목요일
		press(3, 4, 10);
		press(5, 4, 10);
		press(4, 4, 10);
		press(4, 4, 10);
		
		//금요일
		press(5, 5, 9);
		press(3, 5, 9);
		
		//토요일
		
		//일요일
		press(3, 0, 12);
		press(5, 0, 14);
		press(6, 0, 18);
		
		
		System.out.println("월요일 10시");
		dataP.getPrediction(1, 10);
		
		System.out.println("화요일 10시");
		dataP.getPrediction(2, 10);
		
		System.out.println("금요일 9시");
		dataP.getPrediction(5, 9);
		
	}
	
	public static void press(int _floorNo, int _day, int _hour){
		dataP.pressed(_floorNo, _day, _hour);
	}
}

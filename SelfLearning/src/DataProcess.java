import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class DataProcess {
	private static Floor[] floors;
	private static int floorNum;
	
	public DataProcess(int _floorNum){
		int i;
		
		this.floorNum = _floorNum;
		
		floors = new Floor[floorNum+1];
		for (i = 1; i <= floorNum; i++){
			floors[i] = new Floor(i);
		}
		
	}
	
	public void pressed(int _floor, int _day, int _hour){
		Floor currentFloor = floors[_floor];
		
		currentFloor.pressed(_day, _hour);
	}
	
	public void getPrediction(int _day, int _hour){
		HashMap<String, Integer> predictMap;
		List<HashMap<String, Integer>> predictArr = new ArrayList<HashMap<String, Integer>>();
		int i,j;
		
		for (i = 1; i <= floorNum; i++){
			CustomTime currentTime = floors[i].getTimePart(_day, _hour);
			predictMap = new HashMap<String, Integer>();
			
			predictMap.put("floor", i);
			predictMap.put("pressed", currentTime.getPressedNum());
			predictMap.put("loss", 0);
			predictArr.add(predictMap);
		}
		
		for (i = 1; i <= floorNum; i++){
			int testFloor = i;
			int eff = 0;
			for (j = 1; j <= floorNum; j++){
				int pNum = predictArr.get(j-1).get("pressed");
				int abs = testFloor - j;
				if(abs < 0){
					abs = -abs;
				}
				
				eff += abs*pNum;
			}
			predictArr.get(testFloor-1).put("loss", eff);
		}
		Collections.sort(predictArr, new FloorAscCompare());
		
		System.out.println(predictArr.get(0));
		System.out.println(predictArr.get(1));
		System.out.println(predictArr.get(2));
		
	}
	static class FloorAscCompare implements Comparator<HashMap<String, Integer>> {
		 
		/**
		 * 오름차순(ASC)
		 */

		@Override
		public int compare(HashMap<String, Integer> o1, HashMap<String, Integer> o2) {
			return o1.get("loss") < o2.get("loss") ? -1 : o1.get("loss") > o2.get("loss") ? 1:0;
		}
 
	}
}

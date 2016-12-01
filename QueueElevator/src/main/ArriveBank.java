package main;

public class ArriveBank {
	int board[][] = new int[7][7];//depart, arrive floor
	public ArriveBank(){
		for(int i = 0 ; i < board.length; i++){
			for(int k = 0; k < board.length; k++)
				board[i][k] = 0;
		}
	}
	
	public void setActive(int depart, int arrive){
		board[depart][arrive]++;
	}
	public void setAllInActive(int depart){
		for(int i = 0; i < board.length; i++)
			board[depart][i] = 0;
	}
	public int getActiveState(int depart, int arrive){
		return board[depart][arrive];
	}
	
}

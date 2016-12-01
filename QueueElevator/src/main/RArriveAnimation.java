package main;

import javax.swing.JLabel;

import util.SetIconToLabel;


public class RArriveAnimation extends Thread{
	static int total = 0;
	int startX,startY;
	JLabel lb;
	Main parent;
	int ves = 1;
	int delay;
	public RArriveAnimation(Main parent,int floor, int delay){//start point
		startX = 480+10;
		startY = 168-30 +(6-floor)*86;
		lb = new JLabel();
		lb.setBounds(startX,startY,55,55);
		SetIconToLabel.set(lb,"img/ghost.png",55,55);
		parent.bgLb.add(lb);
		this.parent = parent;
		this.delay = delay;
		total++;
	}
	@Override
	public void run() {
		try{
			sleep(200 * delay);
			// TODO Auto-generated method stub
			for(int i = 0 ; i < 12; i++){
				sleep(100);
				startX-= 10;
				if(i % 5 == 0)ves *= -1;
				startY = startY + 3 * ves;
				lb.setLocation(startX, startY);
			}
		}catch(Exception e){}
		parent.bgLb.remove(lb);
	}



}

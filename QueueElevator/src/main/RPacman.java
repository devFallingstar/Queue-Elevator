package main;

import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JLabel;

import util.SetIconToLabel;

public class RPacman extends Thread{
	private JLabel lb;
	private RSection section;
	int speed; //fastest : 1 , slowest : 5
	int currentSct;
	boolean canMove;
	boolean stopBy;
	boolean stop;
	boolean loadStop;
	boolean canBack;
	int preFloor;
	public RPacman(JLabel lb, JButton callBtn[],int currentSct,int spd){
		this.speed = spd;
		this.lb = lb;
		this.currentSct = currentSct;
		this.canMove = false;
		this.stopBy = false;
		this.stop = false;
		this.canBack = false;
		this.loadStop = false;
		this.preFloor = -1;
		section = new RSection();
		lb.setLocation(section.getSectionPoint(currentSct));
		SetIconToLabel.set(lb, "img/pacman_d.png", 75, 75);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.flush();
			if(canMove == true){
				if(stopBy == true && stop == false){
					stopByMove();
				}
				else if(stopBy == false && loadStop == false)move();
				else if(stop == true && canBack == true)backMove();
				else if(stop == true && canBack == false){canMove = false;}
				else canMove = false;
			}
		}
	}
	
	public int sectionToFloor(int sec){
		if(sec == 2) return 0;
		else if(sec == 3) return 1;
		else if(sec == 4) return 2;
		else if(sec == 5) return 3;
		else if(sec == 6) return 4;
		else if(sec == 7) return 5;
		if(sec == 8) return 6;
		return  -1;
	}
	private void backMove(){
		SetIconToLabel.set(lb, "img/pacman_r.png", 75, 75);
		
		Point nextPoint;
		nextPoint = section.getSectionPoint(currentSct-12);
		
		Point currentPoint = lb.getLocation();
		while((currentPoint .x != nextPoint.x) || (currentPoint.y != nextPoint.y)){
			if(currentPoint .x < nextPoint.x){
				lb.setLocation(currentPoint.x+1, currentPoint.y);
			}else if(currentPoint.x > nextPoint.x){
				lb.setLocation(currentPoint.x-1, currentPoint.y);
			}else if(currentPoint.y < nextPoint.y){
				lb.setLocation(currentPoint.x, currentPoint.y+1);
			}else if(currentPoint.y > nextPoint.y){
				lb.setLocation(currentPoint.x, currentPoint.y-1);
			}
			currentPoint = lb.getLocation();
			try{
				sleep(speed);
			}catch(Exception e){}
		}
		
		currentSct -= 12;
		stop = false;
		stopBy = false;
		canMove = false;
		canBack = false;
	}
	private void stopByMove(){
		SetIconToLabel.set(lb, "img/pacman_l.png", 75, 75);
		
		Point nextPoint;
		nextPoint = section.getSectionPoint(currentSct+12);
		
		Point currentPoint = lb.getLocation();
		while((currentPoint .x != nextPoint.x) || (currentPoint.y != nextPoint.y)){
			if(currentPoint .x < nextPoint.x){
				lb.setLocation(currentPoint.x+1, currentPoint.y);
			}else if(currentPoint.x > nextPoint.x){
				lb.setLocation(currentPoint.x-1, currentPoint.y);
			}else if(currentPoint.y < nextPoint.y){
				lb.setLocation(currentPoint.x, currentPoint.y+1);
			}else if(currentPoint.y > nextPoint.y){
				lb.setLocation(currentPoint.x, currentPoint.y-1);
			}
			currentPoint = lb.getLocation();
			try{
				sleep(speed);
			}catch(Exception e){}
		}
		preFloor = sectionToFloor(currentSct);
		currentSct += 12;
		stop = true;
		canMove = false;
		canBack = false;
	}

	private void move(){
		//img trans
		if(currentSct == 1)
			SetIconToLabel.set(lb, "img/pacman_l.png", 75, 75);
		else if(currentSct == 8) SetIconToLabel.set(lb, "img/pacman_r.png", 75, 75);
		else if(currentSct > 8 || currentSct < 1) SetIconToLabel.set(lb, "img/pacman_d.png", 75, 75);
		else SetIconToLabel.set(lb, "img/pacman_u.png", 75, 75);
		
		Point nextPoint;
		nextPoint = section.getSectionPoint((currentSct+1)%RSection.SQUARE_ROUTE_NUM);
		
		Point currentPoint = lb.getLocation();
		while((currentPoint .x != nextPoint.x) || (currentPoint.y != nextPoint.y)){
			if(currentPoint .x < nextPoint.x){
				lb.setLocation(currentPoint.x+1, currentPoint.y);
			}else if(currentPoint.x > nextPoint.x){
				lb.setLocation(currentPoint.x-1, currentPoint.y);
			}else if(currentPoint.y < nextPoint.y){
				lb.setLocation(currentPoint.x, currentPoint.y+1);
			}else if(currentPoint.y > nextPoint.y){
				lb.setLocation(currentPoint.x, currentPoint.y-1);
			}
			currentPoint = lb.getLocation();
			try{
				sleep(speed);
			}catch(Exception e){}
		}
		//pm img trans
		currentSct++;
		if(currentSct > 13){
			currentSct = 0; // one around, reset.
			preFloor = -1;
		}
		canMove = false;

	}
	
	


}

package main;

import javax.swing.JLabel;

public class WaitTimeAnimation extends Thread{
	private JLabel wtLb;
	public WaitTimeAnimation(JLabel obj){
		this.wtLb = obj;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				sleep(500);
				wtLb.setVisible(true);
				sleep(500);
				wtLb.setVisible(false);
			}catch(Exception e){}
		}
	}



}

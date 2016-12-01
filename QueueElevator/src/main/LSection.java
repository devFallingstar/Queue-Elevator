package main;

import java.awt.Point;

public class LSection {
	static final int SQUARE_ROUTE_NUM = 14;
	private Point p[] = new Point[21];

	public LSection(){
		//Square Route
		p[0] = new Point(58,210);
		p[1] = new Point(58,115);
		p[2] = new Point(160,115);
		p[3] = new Point(160,210);
		p[4] = new Point(160,300);
		p[5] = new Point(160,385);
		p[6] = new Point(160,470);
		p[7] = new Point(160,555);
		p[8] = new Point(160,640);
		p[9] = new Point(58,640);
		p[10] = new Point(58,555); 
		p[11] = new Point(58,470); 
		p[12] = new Point(58,385);
		p[13] = new Point(58,300); 
		//
		p[14] = new Point(240,115);
		p[15] = new Point(240,210);
		p[16] = new Point(240,300);
		p[17] = new Point(240,380);
		p[18] = new Point(240,470);
		p[19] = new Point(240,555);
		p[20] = new Point(240,640);
		
	}

	public Point getSectionPoint(int n){
		return p[n];
	}
}

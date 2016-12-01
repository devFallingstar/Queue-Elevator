package main;

import java.awt.Point;

public class RSection {
	static final int SQUARE_ROUTE_NUM = 14;
	private Point p[] = new Point[21];

	public RSection(){
		//Square Route
		//500+14
		p[0] = new Point(674,555); //1
		p[1] = new Point(674,640); //2
		p[2] = new Point(572,640); //3
		p[3] = new Point(572,555); //4
		p[4] = new Point(572,470); //5
		p[5] = new Point(572,385);//6
		p[6] = new Point(572,300); //7
		p[7] = new Point(572,210);//8
		p[8] = new Point(572,115);//9
		p[9] = new Point(674,115);//10
		p[10] = new Point(674,210);//11
		p[11] = new Point(674,300);//12
		p[12] = new Point(674,385);//13
		p[13] = new Point(674,470); //0
		//
		p[14] = new Point(492,640);
		p[15] = new Point(492,555);
		p[16] = new Point(492,470);
		p[17] = new Point(492,380);
		p[18] = new Point(492,300);
		p[19] = new Point(492,210);
		p[20] = new Point(492,115);


		
	}

	public Point getSectionPoint(int n){
		return p[n];
	}
}

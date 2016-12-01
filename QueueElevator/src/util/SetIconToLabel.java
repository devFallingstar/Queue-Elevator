package util;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class SetIconToLabel {
	public SetIconToLabel(){}
	//w,h : resizing image size
	public static void set(JLabel jl,String path,int w,int h){
		ImageIcon icon;
		Image tmp,newImg;
		icon = new ImageIcon(path);
		tmp = icon.getImage();
		newImg = tmp.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
		jl.setIcon(new ImageIcon(newImg));
	}
}

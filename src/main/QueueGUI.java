package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.SetIconToLabel;
public class QueueGUI extends JFrame{
	final static int WIDTH = 1200;
	final static int HEIGHT = 800;
	public QueueGUI(){
		initComponents();
		setCenterScreen();
	}

	private void initComponents(){
		setLayout(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(WIDTH,HEIGHT));
		setTitle("GUI");

		//Main Panel contains all image Labels
		JPanel mPanel = new JPanel();
		mPanel.setSize(new Dimension(WIDTH,HEIGHT));
		mPanel.setBackground(Color.BLUE);
		mPanel.setLayout(null);

		//Black BG
		JLabel bgLb = new JLabel();
		bgLb.setBounds(0, 0, WIDTH, HEIGHT);
		mPanel.setBackground(Color.BLACK);
		bgLb.setOpaque(false);
		mPanel.add(bgLb);
		//

		//Elevator
		JLabel elvLb = new JLabel();
		elvLb.setBounds(0, 60, WIDTH, HEIGHT);
		SetIconToLabel.set(elvLb, "img/struct/elv.png", WIDTH, HEIGHT);
		elvLb.setOpaque(false);
		bgLb.add(elvLb);
		//

		//top Packmans image
		JLabel pmLb = new JLabel();
		pmLb.setBounds(250,-80,850, 315);
		SetIconToLabel.set(pmLb, "img/pms.png", 300, 150);
		pmLb.setOpaque(false);
		bgLb.add(pmLb);
		//
		
		//score
		JLabel scLb = new JLabel();
		scLb.setBounds(850,-50,300,200);
		SetIconToLabel.set(scLb, "img/score.png", 250, 100);
		scLb.setOpaque(false);
		bgLb.add(scLb);
		//
		
		//buttons
		for(int i = 0; i < 7; i++){
			for(int k = 0; k < 4; k++){
				ImageIcon icon;
				JButton b1 = new JButton();
				b1.setBounds(820+(k*80),120+(i*86),60,60);
				if(k >= 2){
					icon = new ImageIcon("img/struct/down_0.png");
				}
				else{
					icon = new ImageIcon("img/struct/up_0.png");
				}
				b1.setOpaque(false);
				b1.setContentAreaFilled(false);
				b1.setBorderPainted(false);
				b1.setIcon(icon);
				bgLb.add(b1);
			}
		}
		
		add(mPanel);
		setVisible(true);
		pack();
	}

	private void setCenterScreen(){
		Dimension ds = getPreferredSize();
		int width = (int)ds.getWidth();
		int height = (int)ds.getHeight(); 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - width) / 2, (screenSize.height - height) / 2);
	}
}

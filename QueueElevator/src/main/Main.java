package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import util.SetIconToLabel;
public class Main extends JFrame{
	final static int WIDTH = 1200;
	final static int HEIGHT = 800;

	public JButton lElevBtn[] = new JButton[7];
	public JButton rElevBtn[] = new JButton[7];

	public JLabel LFloorLb[] = new JLabel[7];
	public JLabel RFloorLb[] = new JLabel[7];

	public JLabel bgLb;
	public JLabel notice;
	public JLabel wtTxtLb;

	int mode;
	int spd;
	private TopManager topManager;
	private LPacmanManager LpmManager;
	private RPacmanManager RpmManager;
	public Main(int mode,int spd){
		this.mode = mode;//1 : finite, 0: infinite
		this.spd = spd;
		initComponents();
		setCenterScreen();
	}

	private void initComponents(){
		setLayout(null);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(WIDTH,HEIGHT));
		wtTxtLb = new JLabel();
		LpmManager = new LPacmanManager(this,mode);
		RpmManager = new RPacmanManager(this,mode);
		topManager = new TopManager(mode,wtTxtLb,LpmManager,RpmManager);
		//Main Panel contains all image Labels
		JPanel mPanel = new JPanel();
		mPanel.setSize(new Dimension(WIDTH,HEIGHT));
		mPanel.setBackground(Color.BLUE);
		mPanel.setLayout(null);

		//Black BG
		bgLb = new JLabel();
		bgLb.setBounds(0, 0, WIDTH, HEIGHT);
		mPanel.setBackground(Color.BLACK);
		bgLb.setOpaque(false);
		mPanel.add(bgLb);
		//

		//lElevBtn
		for(int i = 6; i >= 0 ; i--){
			lElevBtn[i] = new JButton();
			lElevBtn[i].setBounds(820,120+((6-i)*86),60,60);
			ImageIcon icon = new ImageIcon("img/struct/down_0.png");
			lElevBtn[i].setOpaque(false);
			lElevBtn[i].setContentAreaFilled(false);
			lElevBtn[i].setBorderPainted(false);
			lElevBtn[i].setIcon(icon);
			lElevBtn[i].setToolTipText(""+i);//identify floor
			bgLb.add(lElevBtn[i]);
		}

		//rElevBtn				
		for(int i = 6; i >= 0 ; i--){		
			rElevBtn[i] = new JButton();		
			rElevBtn[i].setBounds(910,120+((6-i)*86),60,60);		
			ImageIcon icon = new ImageIcon("img/struct/up_0.png");		
			rElevBtn[i].setOpaque(false);		
			rElevBtn[i].setContentAreaFilled(false);		
			rElevBtn[i].setBorderPainted(false);		
			rElevBtn[i].setIcon(icon);		
			rElevBtn[i].setToolTipText(""+i);//identify floor			
			bgLb.add(rElevBtn[i]);		
		}

		//LFloorLbs
		for(int i = 6; i >= 0; i--){
			LFloorLb[i] = new JLabel();
			LFloorLb[i].setBounds(240+10,165-30 +(6-i)*86,55,55);
			LFloorLb[i].setOpaque(false);
		}
		//RFloorLbs		
		for(int i = 6; i >= 0; i--){		
			RFloorLb[i] = new JLabel();		
			RFloorLb[i].setBounds(490+10,165-30 +(6-i)*86,55,55);		
			RFloorLb[i].setOpaque(false);		
		}

		//Lpacman1
		JLabel Lpm1Lb = new JLabel();
		Lpm1Lb.setOpaque(false);
		Lpm1Lb.setSize(75,75);
		bgLb.add(Lpm1Lb);
		LPacman Lpm1 = new LPacman(Lpm1Lb,lElevBtn,8,spd);
		LpmManager.addPacman(Lpm1);
		//
		//Lpacman2
		JLabel Lpm2Lb = new JLabel();
		Lpm2Lb.setOpaque(false);
		Lpm2Lb.setSize(75,75);
		bgLb.add(Lpm2Lb);
		LPacman Lpm2 = new LPacman(Lpm2Lb,lElevBtn,9,spd);
		LpmManager.addPacman(Lpm2);
		//
		//Lpacman3
		JLabel Lpm3Lb = new JLabel();
		//		pm3Lb.setOpaque(false);
		Lpm3Lb.setSize(75,75);
		bgLb.add(Lpm3Lb);
		LPacman Lpm3 = new LPacman(Lpm3Lb,lElevBtn,10,spd);
		LpmManager.addPacman(Lpm3);
		//

		//Rpacman1
		JLabel Rpm1Lb = new JLabel();
		Rpm1Lb.setOpaque(false);
		Rpm1Lb.setSize(75,75);
		bgLb.add(Rpm1Lb);
		RPacman Rpm1 = new RPacman(Rpm1Lb,rElevBtn,8,spd);
		RpmManager.addPacman(Rpm1);
		//
		//Lpacman2
		JLabel Rpm2Lb = new JLabel();
		Rpm2Lb.setOpaque(false);
		Rpm2Lb.setSize(75,75);
		bgLb.add(Rpm2Lb);
		RPacman Rpm2 = new RPacman(Rpm2Lb,rElevBtn,9,spd);
		RpmManager.addPacman(Rpm2);
		//
		//Lpacman3
		JLabel Rpm3Lb = new JLabel();
		//		pm3Lb.setOpaque(false);
		Rpm3Lb.setSize(75,75);
		bgLb.add(Rpm3Lb);
		RPacman Rpm3 = new RPacman(Rpm3Lb,rElevBtn,10,spd);
		RpmManager.addPacman(Rpm3);
		//



		//floor lable
		for(int i = 6; i >= 0; i--){
			bgLb.add(LFloorLb[i]);
			bgLb.add(RFloorLb[i]);
		}

		//Elevator
		JLabel elvLb = new JLabel();
		elvLb.setBounds(0, 60, WIDTH, HEIGHT);
		SetIconToLabel.set(elvLb, "img/struct/elv.png", WIDTH, HEIGHT);
		elvLb.setOpaque(false);
		bgLb.add(elvLb);
		//

		//noticeBg
		JLabel noticeBg = new JLabel();
		noticeBg.setBounds(165,10,500, 70);
		SetIconToLabel.set(noticeBg, "img/noticebg.png", 500, 70);

		notice = new JLabel();
		notice.setBounds(0,0,500, 70);
		notice.setForeground(Color.YELLOW);
		notice.setFont(notice.getFont().deriveFont(30f));
		notice.setHorizontalAlignment(SwingConstants.CENTER);
		noticeBg.add(notice);

		bgLb.add(noticeBg);

		//score
		JLabel scLb = new JLabel();
		scLb.setBounds(750,-55,500,200);
		SetIconToLabel.set(scLb, "img/wt.png", 250, 100);
		scLb.setOpaque(false);

		//wtText
		wtTxtLb.setBounds(235,53,500,100);
		wtTxtLb.setForeground(Color.WHITE);
		wtTxtLb.setFont(wtTxtLb.getFont().deriveFont(45f));
		scLb.add(wtTxtLb);

		bgLb.add(scLb);

		add(mPanel);



		//
		LpmManager.start();
		RpmManager.start();

		topManager.start();
		
		if(mode== 0){
			//Thread
		}
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

	private class LElevBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//			JButton tmp = (JButton)e.getSource();
			//			int floor = Integer.parseInt(tmp.getToolTipText());
			//			if(pmManager.getCall(floor) == 0){
			//
			//				ImageIcon icon = new ImageIcon("img/struct/down_1.png");
			//				tmp.setIcon(icon);
			//			}
		}

	}
}

package run;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Main;

public class Run extends JFrame{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Run();
	}

	public Run(){
		initComponents();
		setCenterScreen();
	}

	private void initComponents(){
		setTitle("MODE");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		JButton m1 = new JButton("INFINITY (Mobile)");
		JButton m2 = new JButton("FINITE (Prepared Request)");
		//m1
		
		m1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int spd;
				spd = Integer.parseInt(getSpeed());
				new Main(0,spd);
				dispose();
			}
			
		});
		
		m2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int spd;
				spd = Integer.parseInt(getSpeed());
				new Main(1,spd);
				dispose();
			}

		});
		add(m1);
		add(m2);
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

	private String getSpeed() {
		return JOptionPane.showInputDialog(
				this,
				"[1] : fastest ~ [5] : slowest",
				"Speed",
				JOptionPane.PLAIN_MESSAGE);
	}
}

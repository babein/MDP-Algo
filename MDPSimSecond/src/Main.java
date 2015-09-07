import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import exploration.SimRobot;
import exploration.Explorer;
import map.CoveredMap;
import map.RealMap;


public class Main {
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//set up the background
		JFrame f = new JFrame();
		f.setTitle("Real Map ");
		f.setSize(new Dimension(750,600));
		
		final RealMap realMap = new RealMap();

		Container contentPane = f.getContentPane();
		contentPane.add(realMap, BorderLayout.CENTER);
		
		// Cover button
		JPanel buttonPanel = new JPanel();
		JButton coverButton = new JButton("Explore");
		coverButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				SimRobot bot = new SimRobot(15,8,15);
				System.out.println("bot created!");
				CoveredMap coveredMap = new CoveredMap(realMap,bot);
				Explorer exp = new Explorer(bot, 8,coveredMap);
				System.out.println("Explorer created!");
				

				exp.go();
				
			}

		});
		
		buttonPanel.add(coverButton);
		
		// Button to create a new map
		JButton newMapButton = new JButton("New Map");
		newMapButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				realMap.removeWalls();
			}

		});
		
		buttonPanel.add(newMapButton);
		
		//add the button panel
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		// Display the window.
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

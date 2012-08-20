package eomprogramming.algebratiles;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class MainGUI extends JFrame implements ActionListener
{
	JLabel welcomeLabel;
	JButton easyButton, mediumButton, hardButton;
	public MainGUI()
	{
		super("Menu");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		setSize(400, 650);
		setLayout(null);
		setResizable(false);
		getContentPane().setBackground(Color.PINK);
		
		makeButtons();
		
		setVisible(true);
		repaint();
	}
	
	public void makeButtons()
	{
		welcomeLabel = new JLabel("Algebra Tiles");
		welcomeLabel.setBounds(50, 25, 400, 100);
		welcomeLabel.setFont(new Font("Impact", Font.BOLD, 20));
		add(welcomeLabel);
		
		easyButton = new JButton("Level 2");
		easyButton.setBounds(50, 275, 300, 50);
		easyButton.setAlignmentX(CENTER_ALIGNMENT);
		easyButton.setForeground(Color.RED);
		easyButton.setBackground(Color.GREEN);
		easyButton.setFont(new Font("Impact", Font.BOLD, 20));
		easyButton.addActionListener(this);
		add(easyButton);
		
		mediumButton = new JButton("Level 3");
		mediumButton.setBounds(50, 350, 300, 50);
		mediumButton.setAlignmentX(CENTER_ALIGNMENT);
		mediumButton.setForeground(Color.RED);
		mediumButton.setBackground(Color.GREEN);
		mediumButton.setFont(new Font("Impact", Font.BOLD, 20));
		mediumButton.addActionListener(this);
		add(mediumButton);
		
		hardButton = new JButton("Level 4");
		hardButton.setBounds(50, 425, 300, 50);
		hardButton.setAlignmentX(CENTER_ALIGNMENT);
		hardButton.setForeground(Color.RED);
		hardButton.setBackground(Color.GREEN);
		hardButton.setFont(new Font("Impact", Font.BOLD, 20));
		hardButton.addActionListener(this);
		add(hardButton);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == easyButton)
		{
			
		}
		else if(e.getSource() == mediumButton)
		{
			
		}
		else if(e.getSource() == hardButton)
		{
			
		}
	}
}

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class hawtGUI extends JFrame implements ActionListener
{
	JLabel welcomeLabel;
	JButton easyButton, mediumButton, nightmareButton;
	public hawtGUI()
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
		welcomeLabel = new JLabel("Hao are Wei so Good at Factoring?");
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
		
		nightmareButton = new JButton("Level 4");
		nightmareButton.setBounds(50, 425, 300, 50);
		nightmareButton.setAlignmentX(CENTER_ALIGNMENT);
		nightmareButton.setForeground(Color.RED);
		nightmareButton.setBackground(Color.GREEN);
		nightmareButton.setFont(new Font("Impact", Font.BOLD, 20));
		nightmareButton.addActionListener(this);
		add(nightmareButton);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == easyButton)
		{
			
		}
		else if(e.getSource() == mediumButton)
		{
			
		}
		else if(e.getSource() == nightmareButton)
		{
			
		}
	}
}

package quizAppliication;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

class RuleWindow extends JFrame {
	
	//Reference Variables
	private String playerName;
	private String quizTopic;
	private JLabel welcomeLabel, ruleLabel;
	private Font font;
	private JCheckBox checkBox;
	private JButton continueButton;
	private boolean agreed;
	
	//constructor
	public RuleWindow(Login loginrReference, String playerName, String quizTopic) {
		this.playerName = playerName;
		this.quizTopic = quizTopic;
		loginrReference.dispose();;
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.getContentPane().setBackground(Color.WHITE);
		//super.setIconImage(new ImageIcon(getClass().getResource("/Icon.png")).getImage());
		//super.setIconImage(new ImageIcon(getClass().getResource("/res/Icon.png")).getImage());
		File iconFile = new File("res/Icon.png");

		if (iconFile.exists()) {
			super.setIconImage(new ImageIcon(iconFile.getAbsolutePath()).getImage());
		} else {
			System.err.println("Icon file not found: " + iconFile.getAbsolutePath());
		}

		super.setLayout(null);
		super.setSize(1000,500);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (screenSize.width - super.getWidth())/2;
		int centerY = (screenSize.height - super.getHeight())/2;
		super.setLocation(centerX,centerY);
		super.setResizable(false);
		
		this.prepareGUI();
		
		super.setVisible(true);
	}
	
	private void prepareGUI() {
		//font handling
		font = new Font("Verdana",Font.BOLD,24);
		
		//handling welcome text
		welcomeLabel = new JLabel(playerName + ", go through the rules given below:");
		welcomeLabel.setBounds(40,5,800,80);
		welcomeLabel.setForeground(new Color(248,0,78));
		welcomeLabel.setFont(font);
		
		//rule label handling
		ruleLabel = new JLabel();
		ruleLabel.setBounds(40,55,800,300);
		ruleLabel.setFont(new Font("Verdana", Font.PLAIN, 18));;
		ruleLabel.setText(
				"<html>" +
				"1. There will be 10 questions in the quiz, each carrying 4 marks." + "<br><br>" +
				"2. For each question you will have 20 seconds time to answer." + "<br><br>" +
				"3. Clock will be displayed at the top right corner of the screen." + "<br><br>" +
				"4. You can use 50-50 life-line for two questions only." + "<br><br>" +
				"5. At the end, you will be getting a report card for your performance." + "<br><br>" +
				"</html>"
		);
		
		//handling checkbox
		
		checkBox = new JCheckBox("I have read the rules stated above.");
		checkBox.setBackground(Color.white);
		checkBox.setFocusable(false);
		checkBox.setIconTextGap(10);
		checkBox.setFont(new Font("Verdana", Font.BOLD, 16));
		checkBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkBox.setBounds(40,330,800,40);
		checkBox.addActionListener(event -> agreed = checkBox.isSelected());
		
		//handling button
		continueButton = new JButton("CONTINUE");
		continueButton.setFont(font);
		continueButton.setBackground(new Color(248,0,78));
		continueButton.setForeground(Color.black);
		continueButton.setFocusable(false);
		continueButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		continueButton.setBorder(BorderFactory.createLineBorder(new Color(248,0,78),4,true));
		continueButton.setBounds(400,375,200,50);
		continueButton.addActionListener(event -> {if(agreed) {
														new QuizPage(this, playerName, quizTopic);
													}else {
														JOptionPane.showMessageDialog(this,"Have you not read the rules!","Warning",JOptionPane.WARNING_MESSAGE);
													}
		});
		
		
		this.getContentPane().add(welcomeLabel);
		this.getContentPane().add(ruleLabel);
		this.getContentPane().add(checkBox);
		this.getContentPane().add(continueButton);
		
	}

}

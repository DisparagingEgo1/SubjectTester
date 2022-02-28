package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tests.*;

public class NewTestSuiteFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final Test[] easyTests = new Test[] {new Multiplication_Whole_Easy_Test(),new Division_Even_Easy_Test(),new Fractions_Same_Base_Test()};//,new Fractions_Same_Base_Test()
	private static final Test[] mediumTests = new Test[] {new Multiplication_Whole_Medium_Test(),new Division_Even_Medium_Test()};
	private static final Test[] hardTests = new Test[] {new Multiplication_Whole_Hard_Test(),new Division_Even_Hard_Test()};
	private ArrayList<JCheckBox> options;
	private Root mainFrame;
	
	public NewTestSuiteFrame(final String name,final Root mainFrame) {
		super(name);
		this.mainFrame = mainFrame;
		this.mainFrame.setEnabled(false);
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addWindowListener(new TestSuiteWindowListener(this.mainFrame));
		this.setLocationRelativeTo(this.mainFrame);
		
		
		JPanel mainPanel = new JPanel();
		JPanel tests = new JPanel();
		tests.setLayout(new GridLayout());
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		//Maximum size of all testing arrays for formatting
		int maxTestSize =NewTestSuiteFrame.easyTests.length;
		if(maxTestSize < NewTestSuiteFrame.mediumTests.length)maxTestSize = NewTestSuiteFrame.mediumTests.length;
		if(maxTestSize < NewTestSuiteFrame.hardTests.length)maxTestSize = NewTestSuiteFrame.hardTests.length;
		
		//easy tests
		this.options = new ArrayList<JCheckBox>();
		JPanel easyLabelPanel = new JPanel();
		easyLabelPanel.setLayout(new GridLayout());
		JLabel easyLabel = new JLabel("<html><u>Easy Tests</u>");
		easyLabel.setFont(new Font("Tahoma",Font.BOLD,14));
		easyLabelPanel.add(easyLabel);
		mainPanel.add(easyLabelPanel);
		int count = 0;
		for(int i = 0; i < maxTestSize; i++) {
			JCheckBox newItem = null;
			if(i <= NewTestSuiteFrame.easyTests.length -1) {
				newItem = new JCheckBox(NewTestSuiteFrame.easyTests[i].getName());
				this.options.add(newItem);
			}
			else {
				newItem = new JCheckBox();
				newItem.setEnabled(false);
				newItem.setVisible(false);
			}
			tests.add(newItem);
			count++;
			if(count == 4 ||i== maxTestSize-1) {
				mainPanel.add(tests);
				tests = new JPanel();
				tests.setLayout(new GridLayout());
				count = 0;
			}
			
		}
		
		//medium tests
		JPanel mediumLabelPanel = new JPanel();
		mediumLabelPanel.setLayout(new GridLayout());
		JLabel mediumLabel = new JLabel("<html><u>Medium Tests</u>");
		mediumLabel.setFont(new Font("Tahoma",Font.BOLD,14));
		mediumLabelPanel.add(mediumLabel);
		mainPanel.add(mediumLabelPanel);
		count = 0;
		for(int i = 0; i < maxTestSize; i++) {
			JCheckBox newItem = null;
			if(i <= NewTestSuiteFrame.mediumTests.length -1) {
				newItem = new JCheckBox(NewTestSuiteFrame.mediumTests[i].getName());
				this.options.add(newItem);
			}
			else {
				newItem = new JCheckBox();
				newItem.setEnabled(false);
				newItem.setVisible(false);
			}
			tests.add(newItem);
			count++;
			if(count == 4 ||i== maxTestSize-1) {
				mainPanel.add(tests);
				tests = new JPanel();
				tests.setLayout(new GridLayout());
				count = 0;
			}
			
		}
		//hard tests
		JPanel hardLabelPanel = new JPanel();
		hardLabelPanel.setLayout(new GridLayout());
		JLabel hardLabel = new JLabel("<html><u>Hard Tests</u>");
		hardLabel.setFont(new Font("Tahoma",Font.BOLD,14));
		hardLabelPanel.add(hardLabel);
		mainPanel.add(hardLabelPanel);
		count = 0;
		for(int i = 0; i < maxTestSize; i++) {
			JCheckBox newItem = null;
			if(i <= NewTestSuiteFrame.hardTests.length -1) {
				newItem = new JCheckBox(NewTestSuiteFrame.hardTests[i].getName());
				this.options.add(newItem);
			}
			else {
				newItem = new JCheckBox();
				newItem.setEnabled(false);
				newItem.setVisible(false);
			}
			tests.add(newItem);
			count++;
			if(count == 4 ||i== maxTestSize-1) {
				mainPanel.add(tests);
				tests = new JPanel();
				tests.setLayout(new GridLayout());
				count = 0;
			}
			
		}
		JPanel submitPanel = new JPanel();
		JButton submit = new JButton("Submit");
		submit.setName("testSuiteSubmit");
		submit.addActionListener(this);
		submitPanel.add(submit);
		mainPanel.add(submitPanel);
		this.add(mainPanel);
		this.pack();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JButton) {
			if(((JButton)e.getSource()).getName().equals("testSuiteSubmit")) {
				for(JCheckBox item: this.options) {
					if(item.isSelected()) {
						boolean found = false;
						int index = 0;
						while(!found) {
							if(index < NewTestSuiteFrame.easyTests.length && NewTestSuiteFrame.easyTests[index].getName().equals(item.getText())) {
								this.mainFrame.tests.add(NewTestSuiteFrame.easyTests[index]);
								found = true;
							}
							else if(!found && index < NewTestSuiteFrame.mediumTests.length && NewTestSuiteFrame.mediumTests[index].getName().equals(item.getText())) {
								this.mainFrame.tests.add(NewTestSuiteFrame.mediumTests[index]);
								found = true;
							}
							else if(!found && index < NewTestSuiteFrame.hardTests.length && NewTestSuiteFrame.hardTests[index].getName().equals(item.getText())) {
								this.mainFrame.tests.add(NewTestSuiteFrame.hardTests[index]);
								found = true;
							}
							else{ index++;}
						}
					}
				}
				this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
			}
		}
	}

}

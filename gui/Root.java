package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

import tests.Test;
import tests.problems.Problem;

public class Root extends JFrame implements ActionListener,FocusListener {
	private static final long serialVersionUID = 1L;
	private Problem workingProblem;
	private Test workingTest;
	private JTextArea feedback;
	private JTextPane question;
	private JTextField answer;
	private JTextArea results;
	private String resultsFileName = "results.txt";
	
	protected ArrayList<Test> tests = new ArrayList<Test>();
	
	public Root(String name) {
		super(name);
		this.setLayout(new BorderLayout());
		
		JPanel menuPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel quizPanel = new JPanel();
		JPanel resultsPanel = new JPanel();
		JPanel messagePanel = new JPanel();
		
		//Menu Panel
		JMenuBar mb = new JMenuBar();
		JMenu file = new JMenu("File");
		//JMenu prefs = new JMenu("Prefs");
		JMenuItem newQuiz = new JMenuItem("New");
		//JMenuItem loadQuiz= new JMenuItem("Load");
		newQuiz.setName("newQuiz");
		newQuiz.addActionListener(this);
		file.add(newQuiz);
		//file.add(loadQuiz);
		mb.add(file);
		//mb.add(prefs);
		menuPanel.add(mb);
		menuPanel.setLayout(new GridLayout(1,1));
		
		//Quiz and Results Panels
		
		JTabbedPane jtp = new JTabbedPane();
		jtp.add("Test", quizPanel);
		jtp.add("Results",resultsPanel);
		this.question = new JTextPane();
		this.question.setEditable(false);
		this.question.setBackground(Color.LIGHT_GRAY);
		this.question.setFont(new Font("Tahoma",Font.BOLD,35));
		JPanel questionPanel = new JPanel();
		questionPanel.setBackground(Color.LIGHT_GRAY);
		questionPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.insets = new Insets(0,0,-75,0);//top,left,bottom,right
		questionPanel.add(this.question,gbc);
		quizPanel.setLayout(new GridLayout(2,2));
		quizPanel.add(questionPanel);
		this.answer = new JTextField("Answer",15);
		this.answer.addFocusListener(this);
		this.answer.setName("Answer");
		this.answer.setHorizontalAlignment(JTextField.CENTER);
		this.answer.setFont(new Font("Tahoma",Font.PLAIN,16));
		JPanel submitForAnswerPanel = new JPanel();
		submitForAnswerPanel.setLayout(new GridLayout(3,1));
		JButton submitForAnswer = new JButton("Submit");//listen for input
		submitForAnswer.setName("submitForAnswer");
		submitForAnswer.addActionListener(this);
		submitForAnswerPanel.add(new JPanel());
		submitForAnswerPanel.add(new JPanel());
		submitForAnswerPanel.add(submitForAnswer);
		quizPanel.add(submitForAnswerPanel);
		quizPanel.add(submitForAnswerPanel);
		quizPanel.add(answer);
		
		this.results = new JTextArea();
		this.results.setLineWrap(true);
		resultsPanel.setLayout(new GridLayout(1,1));
		resultsPanel.add(this.results);
		
		mainPanel.add(jtp);
		mainPanel.setLayout(new GridLayout(1,1));
		
		//Message Panel
		
		this.feedback = new JTextArea();//listener
		//this.feedback.setPreferredSize(new Dimension(200,400));
		this.feedback.setEditable(false);
		this.feedback.setLineWrap(true);
		this.feedback.setWrapStyleWord(true);
		JScrollPane scroll = new JScrollPane(this.feedback);
		scroll.setPreferredSize(new Dimension(200,350));
		
		messagePanel.add(scroll);
		
		this.add(menuPanel, BorderLayout.PAGE_START);
		this.add(mainPanel,BorderLayout.CENTER);
		this.add(messagePanel,BorderLayout.LINE_END);
		this.setSize(new Dimension(600,400));
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.getRootPane().setDefaultButton(submitForAnswer);
		this.setVisible(true);
		
	}

	public void initializeTest() {
		if(!(this.tests == null || tests.isEmpty())) {
			Random rand = new Random();
			this.workingTest = this.tests.remove(rand.nextInt(this.tests.size()));			
			this.workingProblem = this.workingTest.getNextProblem();
			updateFields();
		}
	}
		
	private void updateFields() {
		try {this.workingProblem.displayProblem(this.question);}
		catch(Exception e) {e.printStackTrace();}
		
		this.feedback.setText(this.workingTest.getName()+"\n"+this.workingTest.getDescription()+"\n"+"Attempted: "+this.workingTest.getTotal()+"  Required Streak: "+this.workingTest.getStreak()+
				"\nCurrent Streak: "+this.workingTest.getScore()+"\n");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JButton) {
			if(this.workingTest == null)Toolkit.getDefaultToolkit().beep();
			else if(((JButton)e.getSource()).getName().equals("submitForAnswer")) {
				if(!this.answer.getText().equals("Answer")) {
					if(this.workingProblem.isCorrect(this.answer.getText())) {
						this.workingTest.setScore(this.workingTest.getScore()+1);
						this.workingTest.setTotalCorrect(this.workingTest.getTotalCorrect()+1);
						this.feedback.setText(this.workingTest.getName()+"\n"+this.workingTest.getDescription()+"\n"+"Attempted: "+this.workingTest.getTotal()+"  Required Streak: "+this.workingTest.getStreak()+
								"\nCurrent Streak: "+this.workingTest.getScore()+"\n\nCorrect!");
						
						
						if(this.workingTest.getScore() == this.workingTest.getStreak()) {
							this.results.setText(this.results.getText() + this.workingTest.getName() + ":\n Correct: "+this.workingTest.getTotalCorrect() + " Attempted: "+this.workingTest.getTotal() + " "+
						String.format("%.2f%%\n\n",((double)this.workingTest.getTotalCorrect()/this.workingTest.getTotal())*100));
							
							if(this.tests.size()==0) {
								((JButton)e.getSource()).setText("Finish");
								((JButton)e.getSource()).setName("Exit");
							}
							else {
								((JButton)e.getSource()).setText("Next Test");
								((JButton)e.getSource()).setName("NextTest");
							}
							
						}
						else {
							((JButton)e.getSource()).setText("Next Question");
							((JButton)e.getSource()).setName("RightAnswer");
						}
						
						
						
					} 
					else {
						this.feedback.setText(this.workingTest.getName()+"\n"+this.workingTest.getDescription()+"\n"+"Attempted: "+this.workingTest.getTotal()+"  Required Streak: "+this.workingTest.getStreak()+
								"\nCurrent Streak: "+this.workingTest.getScore()+"\n\n"+this.workingProblem.showSteps());
						((JButton)e.getSource()).setText("Next Question");
						((JButton)e.getSource()).setName("WrongAnswer");
					}
				}
				else Toolkit.getDefaultToolkit().beep();
			}
			else if(((JButton)e.getSource()).getName().equals("WrongAnswer")) {
				((JButton)e.getSource()).setText("Submit");
				((JButton)e.getSource()).setName("submitForAnswer");
				this.workingTest.setScore(0);
				this.workingProblem = this.workingTest.getNextProblem();
				this.answer.setText("Answer");
				updateFields();
			}
			else if(((JButton)e.getSource()).getName().equals("RightAnswer")) {
				((JButton)e.getSource()).setText("Submit");
				((JButton)e.getSource()).setName("submitForAnswer");
				this.workingProblem = this.workingTest.getNextProblem();
				this.answer.setText("Answer");
				updateFields();
				//update listener for next test
			}
			else if(((JButton)e.getSource()).getName().equals("NextTest")) {
				((JButton)e.getSource()).setText("Submit");
				((JButton)e.getSource()).setName("submitForAnswer");
				this.answer.setText("Answer");
				this.initializeTest();
			}
			else if(((JButton)e.getSource()).getName().equals("Exit")) {
				this.writeResults();
				this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
			}
		}
		else if (e.getSource() instanceof JMenuItem) {
			JMenuItem item = ((JMenuItem)e.getSource());
			if(item.getName().equals("newQuiz")) {
				new NewTestSuiteFrame(this.getTitle(),this);
			}
		}
	}
	
	private void writeResults() {
		try {
			PrintWriter fout = new PrintWriter(this.resultsFileName);
			fout.println(this.results.getText());
			fout.close();
		}
		catch(Exception e) {e.printStackTrace();}
	}
	
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JTextField) {
			if(((JTextField)e.getSource()).getName().equals("Answer")&&((JTextField)e.getSource()).getText().equals("Answer")) {
				this.answer.setText("");
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() instanceof JTextField) {
			if(((JTextField)e.getSource()).getName().equals("Answer") && this.answer.getText().length() == 0) {
				this.answer.setText("Answer");
			}
		}
	}
	public static void main(String[] args) {
		new Root("Subject Tester");
	}
}

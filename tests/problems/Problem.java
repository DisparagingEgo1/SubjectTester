package tests.problems;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

public abstract class Problem{
	protected String problem;
	protected String answer;
	
	public Problem() {
		this.problem = "";
		this.answer = "";
	}
	public String getProblem() {
		return this.problem;
	}
	public String getAnswer() {
		return this.answer;
	}
	public abstract String showSteps();
	public abstract boolean isCorrect(final String answer);
	public abstract void displayProblem(final JTextPane obj)throws BadLocationException;
}

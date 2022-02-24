package tests.problems;

import java.util.Random;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Multiplication_Whole_Hard extends Problem {

	public Multiplication_Whole_Hard() {
		super();
		Random rand = new Random();
		int num1 = rand.nextInt(19)+1;
		if(num1 < 6)num1 = 6;
		int num2 = rand.nextInt(12)+1;
		if(num2 < 6)num2 = 6;
		this.problem = num1 + " " + num2;
		this.answer = (num1*num2)+"";
		// TODO Auto-generated constructor stub
	}

	@Override
	public String showSteps() {
		String result = "";
		int num1 = Integer.parseInt(this.problem.split(" ")[0]),num2 = Integer.parseInt(this.problem.split(" ")[1]);
		result += "Multiplying "+num1 + " by " + num2 + " means we have "+num2+" groups of "+num1+"\n";
		for(int x = 1; x <= num2; x++) {
			if(x == 1) {
				result += " With 1 group of "+num1 + " we have "+num1+"\n";
			}
			else {
				result += " With "+x+" groups of "+num1 + " we have "+num1*x+"\n";
			}
		}
		result += "So "+num1 + " multiplied by " + num2 + " equals "+(num1*num2)+"\n";
		return result;
	}
	@Override
	public String toString() {
		String result;
		
		if(Integer.parseInt(this.problem.split(" ")[0])>9) {
			result = " "+this.problem.split(" ")[0]+"\nX "+this.problem.split(" ")[1]+"\n________\n";
		}
		else {
			result = "  "+this.problem.split(" ")[0]+"\nX "+this.problem.split(" ")[1]+"\n________\n";
		}
		return result;
	}
	@Override
	public boolean isCorrect(final String answer) {
		// TODO Auto-generated method stub
		return this.answer.equals(answer.strip());
	}

	@Override
	public void displayProblem(final JTextPane obj)throws BadLocationException {
		obj.setText("");
		String[] theProblem = this.problem.split(" ");
		SimpleAttributeSet attributeSet = new SimpleAttributeSet();
		StyleConstants.setForeground(attributeSet, obj.getBackground());
		Document doc = obj.getStyledDocument();
		doc.insertString(0, "X ", attributeSet);
		attributeSet = new SimpleAttributeSet();
		//top number formatting
		if(Integer.parseInt(theProblem[0])<=9 && Integer.parseInt(theProblem[1])>9) {
			StyleConstants.setForeground(attributeSet, obj.getBackground());
			doc.insertString(doc.getLength(), ("1"), attributeSet);
			attributeSet = new SimpleAttributeSet();
			doc.insertString(doc.getLength(), (theProblem[0]+"\n"), attributeSet);
			
		}
		else {
			doc.insertString(doc.getLength(), (theProblem[0]+"\n"), attributeSet);
		}
		//bottom number formatting
		StyleConstants.setUnderline(attributeSet, true);
		if(Integer.parseInt(theProblem[1])<=9 && Integer.parseInt(theProblem[0])>9) {
			doc.insertString(doc.getLength(), ("X   "+theProblem[1]+"\n"), attributeSet);
		}
		else {
			doc.insertString(doc.getLength(), ("X "+theProblem[1]+"\n"), attributeSet);	
		}	
	}
}

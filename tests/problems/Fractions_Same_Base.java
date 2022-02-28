package tests.problems;

import java.awt.Font;
import java.util.Random;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Fractions_Same_Base extends Problem {

	public Fractions_Same_Base() {
		super();
		Random rand = new Random();
		int fraction1Numerator, fraction2Numerator;
		int base = rand.nextInt(7)+2;//2-8
		if(base == 2) {
			fraction1Numerator = 1;
			fraction2Numerator = 1;
		}
		//add
		if(rand.nextInt(2)==0) {
			fraction1Numerator = rand.nextInt(base-1)+1;
			fraction2Numerator = rand.nextInt(base - fraction1Numerator)+1;
			this.problem = fraction1Numerator+ " + "+fraction2Numerator+" "+base;
			this.answer = fraction1Numerator + fraction2Numerator + " "+base; 
			if(fraction1Numerator + fraction2Numerator == base) {
				this.answer += " 1";		
			}
			
		}
		//subtract
		else {
			fraction1Numerator = rand.nextInt(base-1)+1;
			fraction2Numerator = rand.nextInt(fraction1Numerator)+1;
			this.problem = fraction1Numerator+ " - "+fraction2Numerator+" "+base;
			this.answer = fraction1Numerator - fraction2Numerator + " "+base; 
			if(fraction1Numerator - fraction2Numerator == 0) {
				this.answer += " 0";		
			}
		}
		
		
		
	}
	
	@Override
	public String showSteps() {
		String result = "";
		String[] ans = this.problem.split(" ");
		if(ans[1].equals("+")) {
			result = "Since our denominators are the same we can treat this as a simple addition problem\n";
			result += ans[0] + " plus "+ans[2] + " equals "+ (Integer.parseInt(ans[0])+Integer.parseInt(ans[2]));
			result += "\nThus, the answer is\n  "+(Integer.parseInt(ans[0])+Integer.parseInt(ans[2]))+"  \n-----";
			if((Integer.parseInt(ans[0])+Integer.parseInt(ans[2]))==(Integer.parseInt(ans[3]))) {
				result += "or 1\n";
			}
			result += "\n  "+ans[3]+"  ";	
		}
		else {
			result = "Since our denominators are the same we can treat this as a simple subtraction problem\n";
			result += ans[0] + " minus "+ans[2] + " equals "+ (Integer.parseInt(ans[0])-Integer.parseInt(ans[2]));
			result += "\nThus, the answer is\n  "+(Integer.parseInt(ans[0])-Integer.parseInt(ans[2]))+"  \n-----";
			if((Integer.parseInt(ans[0])-Integer.parseInt(ans[2]))==0) {
				result += " or 0";
			}
			result += "\n  "+ans[3]+"  ";
		}
		return result;
	}

	@Override
	public boolean isCorrect(final String ans) {
		String[] actualAnswer = this.answer.split(" ");
		if(actualAnswer.length == 3 && actualAnswer[2].equals(ans))return true;
		//assume user input is of the form x/y
		String[] userAnswer = ans.split("/");
		if(userAnswer.length != 2)return false;
		return userAnswer[0].equals(actualAnswer[0]);
	}

	@Override
	public void displayProblem(final JTextPane obj) throws BadLocationException {
		obj.setText("");
		obj.setFont(new Font("Tahoma",Font.BOLD,25));
		String[] theProblem = this.problem.split(" ");
		SimpleAttributeSet attributeSet = new SimpleAttributeSet();
		Document doc = obj.getStyledDocument();
		doc.insertString(0, "  "+theProblem[0]+"            "+theProblem[2]+"  \n", attributeSet);
		doc.insertString(doc.getLength(), "-----  "+theProblem[1]+"  -----\n", attributeSet);
		doc.insertString(doc.getLength(), "  "+theProblem[3]+"            "+theProblem[3]+"  \n", attributeSet);
	}

}

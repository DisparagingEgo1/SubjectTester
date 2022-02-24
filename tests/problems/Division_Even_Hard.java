package tests.problems;

import java.util.Random;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Division_Even_Hard extends Problem {

	public Division_Even_Hard() {
		super();
		Random rand = new Random();
		int num1 = rand.nextInt(143)+2;
		int num2 = rand.nextInt(11)+2;
		while(num1%num2 != 0) {
			num1 = rand.nextInt(143)+2;
			num2 = rand.nextInt(11)+2;
		}
		
		this.problem = num1 + " " + num2;
		this.answer = (num1/num2)+"";
	}
	
	@Override
	public String showSteps() {
		String result = "";
		int num1 = Integer.parseInt(this.problem.split(" ")[0]),num2 = Integer.parseInt(this.problem.split(" ")[1]);
		result += "Dividing "+num1 + " by " + num2 + " means that we want to know how many groups of "+num2+" are equal to "+num1+"\n";
		for(int x = 1; x <= num1/num2; x++) {
			if(x == 1) {
				result += " With 1 group of "+num2 + " we have "+num2+"\n";
			}
			else {
				result += " With "+x+" groups of "+num2 + " we have "+num2*x+"\n";
			}
		}
		result += "So "+num1 + " divided by " + num2 + " equals "+(num1/num2)+"\n";
		return result;
	}

	@Override
	public boolean isCorrect(String answer) {
		return this.answer.equals(answer.strip());
	}

	@Override
	public void displayProblem(JTextPane obj) throws BadLocationException {
		obj.setText("");
		String[] theProblem = this.problem.split(" ");
		SimpleAttributeSet attributeSet = new SimpleAttributeSet();
		StyleConstants.setForeground(attributeSet, obj.getBackground());
		Document doc = obj.getStyledDocument();
		if(Integer.parseInt(theProblem[0])>9)doc.insertString(0, "÷", attributeSet);
		else doc.insertString(0, "÷  ", attributeSet);
		attributeSet = new SimpleAttributeSet();
		//top number formatting
		doc.insertString(doc.getLength(), (theProblem[0]+"\n"), attributeSet);
		//bottom number formatting
		StyleConstants.setUnderline(attributeSet, true);
		if(Integer.parseInt(theProblem[0])>99 && Integer.parseInt(theProblem[1])<=9) {
			doc.insertString(doc.getLength(), ("÷    "+theProblem[1]+"\n"), attributeSet);
		}
		else if(Integer.parseInt(theProblem[0])>9 && Integer.parseInt(theProblem[1])>9) {
			doc.insertString(doc.getLength(), ("÷"+theProblem[1]+"\n"), attributeSet);
		}
		else {
			doc.insertString(doc.getLength(), ("÷  "+theProblem[1]+"\n"), attributeSet);	
		}

	}

}

package tests.problems;

import tests.enums.DifficultyLevel;
import tests.enums.ProblemType;

public class ProblemFactory {

	public Problem getNextProblem(DifficultyLevel l, ProblemType p) {
		switch(l) {
		case EASY:
			switch(p) {
				case MULTIPLICATION_WHOLE:
					return new Multiplication_Whole_Easy();
				case DIVISION_EVEN:
					return new Division_Even_Easy();
			}
			break;
		case MEDIUM:
			switch(p) {
				case MULTIPLICATION_WHOLE:
					return new Multiplication_Whole_Medium();
				case DIVISION_EVEN:
					return new Division_Even_Medium();
			
			}
			break;
		case HARD:
			switch(p) {
				case MULTIPLICATION_WHOLE:
					return new Multiplication_Whole_Hard();
				case DIVISION_EVEN:
					return new Division_Even_Hard();
			
			}
			break;
		}
		return null;
	}
}

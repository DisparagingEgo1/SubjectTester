package tests;

import tests.enums.DifficultyLevel;
import tests.enums.ProblemType;
import tests.problems.Problem;
import tests.problems.ProblemFactory;

public abstract class Test {
	
	protected String name;
	protected String description;
	protected DifficultyLevel level;
	protected ProblemType problem;
	protected ProblemFactory factory;
	private int score;
	private int total;
	private int totalCorrect;
	private int requiredStreak;
	
	public Test(final String name, final String description, final DifficultyLevel level, final ProblemType problem, final int requiredStreak) {
		this.name = name;
		this.description = description;
		this.level = level;
		this.problem = problem;
		this.factory = new ProblemFactory();
		this.score = 0;
		this.total = 0;
		this.totalCorrect = 0;
		this.requiredStreak = requiredStreak;
	}
	public Problem getNextProblem() {
		this.total++;
		return factory.getNextProblem(this.level, this.problem);
	}
	public String getName() {
		return this.name;
	}
	public String getDescription() {
		return this.description;
	}
	public int getScore() {
		return this.score;
	}
	public void setScore(final int score) {
		this.score = score;
	}
	public int getTotal() {
		return this.total;
	}
	public void setTotal(final int total) {
		this.total = total;
	}
	public int getTotalCorrect() {
		return this.totalCorrect;
	}
	public void setTotalCorrect(final int totalCorrect) {
		this.totalCorrect = totalCorrect;
	}
	public int getStreak() {
		return this.requiredStreak;
	}
}

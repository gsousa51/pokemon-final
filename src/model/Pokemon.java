package model;

public enum Pokemon {
	NIDORAN("Nidoran", 41, 235, 55),
	PARAS("Paras", 25, 190, 35),
	DODUO("Doduo", 75, 190, 35),
	VANONAT("Venonat", 45, 190, 60),
	CUBONE("Cubone", 35, 190, 50),
	NIDORINA("Nidorina", 56, 120, 70),
	RYHORN("Ryhorn", 25, 120, 80),
	EXEGGCUTE("Exeggcute", 40, 90, 60),
	PARASECT("Parasext", 30, 75, 60),
	CHANSEY("Chansey", 50, 30, 250);

	private int runRate, currentCatchRate, initialCatchRate;
	private int angryCount, eatingCount;
	private String name;
	private int maxHP, currentHP;

	Pokemon(String name, int runRate, int catchRate, int maxHitPoints) {
		this.runRate = runRate;
		currentCatchRate = initialCatchRate = catchRate;
		angryCount = eatingCount = 0;
		this.name = name;
		maxHP = maxHitPoints;
		currentHP = (int) (Math.random() * maxHitPoints + 1);
	}

	public boolean isCaught() {
		return Math.random() < Math.min(currentCatchRate + 1, 151) / 449.5;
	}

	public boolean isEating() {
		return eatingCount > 0;
	}

	public boolean isAngry() {
		return angryCount > 0;
	}

	public void hitWithRock() {
		angryCount += (int) (Math.random() * 5 + 1);
		eatingCount = 0;
		currentCatchRate = Math.min(255, currentCatchRate * 2);
	}

	public void hitWithBait() {
		eatingCount += (int) (Math.random() * 5 + 1);
		angryCount = 0;
		currentCatchRate /= 2;
	}

	/**
	 * Does the pokemon's turn. Returns true if the pokemon doesn't run away, or
	 * false if it escapes
	 * 
	 * @return boolean battleContinues
	 */
	public boolean doTurn() {
		int runRate = this.runRate;
		if (isAngry()) {
			if (angryCount == 1)
				currentCatchRate = initialCatchRate;
			angryCount--;
			System.out.println("Wild " + name + " is angry!");
			runRate = Math.min(255, runRate * 2);
		} else if (isEating()) {
			System.out.println("Wild " + name + " is eating!");
			eatingCount--;
			runRate = runRate / 4;
		}
		return !((int) (Math.random() * 256) < runRate);
	}
	
	public void heal(int amountHealed) {
		currentHP = Math.min(maxHP, currentHP+amountHealed);
	}
	
	public String toString() {
		return name;
	}
}

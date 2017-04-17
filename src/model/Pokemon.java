package model;

public class Pokemon {

	private int runRate, currentCatchRate, initialCatchRate;
	private int angryCount, eatingCount;
	private String name;
	private int maxHP, currentHP;

	public Pokemon(String name, int runRate, int catchRate, int maxHitPoints) {
		this.runRate = runRate;
		currentCatchRate = initialCatchRate = catchRate;
		angryCount = eatingCount = 0;
		this.name = name;
		maxHP = maxHitPoints;
		currentHP = (int) (0.75 * maxHitPoints);
	}

	/**
	 * Determines if the pokemon is caught or not.
	 * 
	 * @return true if caught, false if not
	 */
	public boolean isCaught() {
		return isCaught(true);
	}

	/**
	 * Method used for testing because isCaught() uses random numbers
	 */
	public boolean isCaught(boolean random) {
		if (random)
			return Math.random() < Math.min(currentCatchRate + 1, 151) / 449.5;
		return 0.2 < Math.min(currentCatchRate + 1, 151) / 449.5;
	}

	/**
	 * Determines if the pokemon is eating bait.
	 * 
	 * @return true if eating, false if not
	 */
	public boolean isEating() {
		return eatingCount > 0;
	}

	/**
	 * Determines if the pokemon is angry at being hit with a rock.
	 * 
	 * @return true if angry, false if not
	 */
	public boolean isAngry() {
		return angryCount > 0;
	}

	/**
	 * Controls pokemon's behavior when hit with a rock.
	 */
	public void hitWithRock() {
		hitWithRock(true);
	}

	/**
	 * Method used for testing because hitWithRock() uses random numbers
	 */
	public void hitWithRock(boolean random) {
		if (random)
			angryCount += (int) (Math.random() * 5 + 1);
		else
			angryCount += 2;
		eatingCount = 0;
		currentCatchRate = Math.min(255, currentCatchRate * 2);
	}

	/**
	 * Controls pokemon's behavior when given bait.
	 */
	public void hitWithBait() {
		hitWithBait(true);
	}

	/**
	 * Method used for testing because hitWithBait() uses random numbers
	 */
	public void hitWithBait(boolean random) {
		if (random)
			eatingCount += (int) (Math.random() * 5 + 1);
		else
			eatingCount += 2;
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
		return doTurn(true);
	}

	/**
	 * Method used for testing because doTurn() uses random numbers
	 */
	public boolean doTurn(boolean random) {
		int runRate = this.runRate;
		if (isAngry()) {
			if (angryCount == 1)
				currentCatchRate = initialCatchRate;
			if (angryCount > 1)
				runRate = Math.min(255, runRate * 2);
			angryCount--;
			System.out.println("Wild " + name + " is angry!");
		} else if (isEating()) {
			System.out.println("Wild " + name + " is eating!");
			if (eatingCount > 1)
				runRate = runRate / 4;
			eatingCount--;
		}
		if (random)
			return !((int) (Math.random() * 256) < runRate);
		return !(45 < runRate);
	}

	/**
	 * Heals the pokemon by the given amount.
	 * 
	 * @param amountHealed
	 */
	public void heal(int amountHealed) {
		currentHP = Math.min(maxHP, currentHP + amountHealed);
	}

	/**
	 * Returns the pokemon's name.
	 * 
	 * @return pokemon name
	 */
	public String toString() {
		return name;
	}

	/**
	 * Returns an array of the pokemon's current and max HP. array[0] is the
	 * current health and array[1] is the max health.
	 * 
	 * @return int[] health
	 */
	public int[] getHealth() {
		return new int[] { currentHP, maxHP };
	}
}

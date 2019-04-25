package primeiraQuestao;

import java.util.concurrent.ThreadLocalRandom;

public class Dice extends Thread {

	int value;
	int min;
	int max;
	String owner;
	Dice winner;
	Semaphore sem;
	/** 
	Default min/max values are 1/20 respectively. Can be updated to any int values using set methods, as long as max value is 
	bigger than min value. If this requirement is not met, setValue method will not update the value of min and/or max.
	
	Winner is used as a refference for the end-value, every thread must reference it since it will be seen by all threads. Since it depends
	on main implementation, we do not set as we initialize die, we do so with manual sets.
	**/
	public Dice ( int value, String owner, Semaphore sem ) {
		this.value = value;
		this.owner = owner;
		min = 1;
		max = 200;
		this.sem = sem;
	}

	public Dice getWinner() {
		return winner;
	}

	public void setWinner(Dice winner) {
		this.winner = winner;
	}

	@Override
	public void run() {
		
		int initialRead = 0;
		int secondRead = 0;
		try {
			
			for (int i = 0; i < 1000; i++ ) {
				rollDice();
				if ( this.value > winner.value ) { 
					/* We have a possible candidate, get Semaphore to update winner value and owner. 
					If it's busy, keep trying until it succeeds. */
					initialRead = this.winner.value;
					// defaults Semaphore permission to false
					boolean semPermission = false;
					
					do {
						 semPermission = sem.getSemaphore(this.owner);
						 
						 if ( semPermission ) {
							// Winner value could have changed since last check, we need to check again
							if ( this.value > winner.value  ) {
								updateWinnerValue(this.owner, this.value, winner.owner, winner.value);
							} else {
								secondRead = this.winner.value;
								System.out.println(this.owner + " got the Semaphore but it read " + initialRead + " and the current value is " 
								+ secondRead + ", and it's rolled value is " + this.value);
					
							}
						 }
					} while ( !semPermission );
					
					// Once the operation is done, clear the Semaphore
					sem.clearSemaphore(this.owner);
				}
				
			}
			
		} catch ( Exception e) {
			System.out.println("An exception has occured! Page Captain Marvel!");
		}
	}
	
	/** Gets a random value between min and max. Only updates DiceValue with greater value **/
	public void rollDice () {
		int roll = ThreadLocalRandom.current().nextInt(min, max + 1);
		//System.out.println(this.owner + " rolled a " + roll);
		if ( roll > this.value ) {
			this.value = roll;
		}
	}

	// Updating operation on winner must be synchronized since it's the critical object.
	public synchronized void updateWinnerValue ( String newOwner, int newValue, String currentOwner, int currentValue) {
		System.out.println(newOwner + " is updating the winner from value " + this.winner.value + " to " + newValue);
		winner.owner = newOwner;
		winner.value = newValue;
	}
	
	public int getValue() {
		return value;
	}
	
	/** Method to manually set Dice value to desired value without rolls. Only cheaters use this. Loki loves it.**/
	public void setValue(int value) {
		this.value = value;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		if ( min < this.max ) {
			this.min = min;
		}
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		if ( this.min > max ) {
			this.max = max;
		}
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}

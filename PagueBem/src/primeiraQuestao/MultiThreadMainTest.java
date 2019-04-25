package primeiraQuestao;

import java.util.concurrent.TimeUnit;

public class MultiThreadMainTest {

	
	/*  Main containing Semaphore test for single-thread */ 
	 
	public static void main(String[] args) throws InterruptedException {
		
		// Initialize all variables.
		
		// Shared objects between threads
		Semaphore sem = new Semaphore();
		Dice winner = new Dice (1, "Thanos", sem);
		
		// independent threads
		Dice tony = new Dice(1, "Tony Stark", sem);
		Dice steve = new Dice(1, "Steve Rogers", sem);
		Dice nat = new Dice (1, "Natasha Romanoff", sem);
		Dice bruce = new Dice (1, "Bruce Banner", sem);
		Dice clint = new Dice (1, "Clint Barton", sem);
		Dice thor = new Dice (1, "Thor Odinson", sem);
		
		// Sets same winner object for all threads.
		tony.setWinner(winner);
		steve.setWinner(winner);
		nat.setWinner(winner);
		bruce.setWinner(winner);
		clint.setWinner(winner);
		thor.setWinner(winner);
		
		/* All dice owners will roll at the same time 10 times and start with minimum value. Everytime a higher roll is rolled,
		   we update the winner */
		
		tony.start();
		steve.start();
		nat.start();
		bruce.start();
		clint.start();
		thor.start();
			
		TimeUnit.SECONDS.sleep(5);
		System.out.println("The winner after 5 seconds is " + winner.owner + " with the value of " + winner.value);
			
	}
	
}

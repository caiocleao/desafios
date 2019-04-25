package primeiraQuestao;

public class Semaphore {

	// true for available, false for not-available.
	// All Semaphore operations must be synchronized for thread safety.
	private boolean available;
	
	/** Initializing, default value as true, method doesn't have default set values to keep value protected by changeValue method **/
	public Semaphore () {
		this.available = true;
	}
	
	/** Updates the semaphore value to it's negative ( If it trues it sets false and vice-versa **/
	public synchronized void changeValue () {
		this.available = !this.available;
	}
	
	/** Returns current value of Semaphore **/
	public synchronized boolean getValue () {
		return this.available;
	}
	
	/** Tries to get Semaphore, if it's busy, returns false. If it's available, set it's value to false so other threads can't
	    get it and returns true for object trying to update critical value. **/
	public synchronized boolean getSemaphore ( String owner) {
		
		if ( this.available ) {
			changeValue();
			return true;
		}
		return false;
	}
	
	public synchronized void clearSemaphore ( String owner) {
		this.available = true;
	}
	
}

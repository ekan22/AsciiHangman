import java.util.Arrays;
public class Man {
	
	/*  An ASCII Hangman looks like this:
	 *   O 
	 *  \|/
	 *  / \
	 */

	protected static final int MAX_INCORRECT = 6; //Number of mistakes allowed before you lose
	private int numIncorrect; //Used to create phases of the Hangman
	private char[] body = new char[12]; //Creates a surface for the hangman to be
	
	public Man() { //This constructor creates an array that is really a 3 by 3 frame of spaces
	    for (int i = 0; i < this.body.length; i++) { 
	        if (i % 4 == 3) {
	          this.body[i] = '\n';
	        } else {
	          this.body[i] = ' ';
	        } 
	    }
	}
				

	

	
	public boolean isAlive() { //Checks if the man is still alive
		return (numIncorrect<MAX_INCORRECT);
	}
			
	
	
	public void hang() { //Creates six phases of the man. Each time the method is called, it progresses onto the next phase
		this.numIncorrect++;
		if(numIncorrect==1) { 
			body[1]='O';
		}else if(numIncorrect==2) {
			body[5]='|';
		}else if(numIncorrect==3) {
			body[4]='\\';
		}else if(numIncorrect==4) {
			body[6]='/';
		}else if(numIncorrect==5) {
			body[8]='/';
		}else if(numIncorrect==6) {
			body[10]='\\';
		}
		
		}
	
	public String toString() { //Converts the array into a string that can be easily printed. This is not actually needed for the game to work, but allows the coder to test if the Man class works
		String s= "";
		for(int i = 0;i<12;i++) {
			s+=body[i];

		}
		return s;
	}
		
		
	protected char[] toCharArray() { //Accessor Method, allows other classes, mainly Gallows to access the body array
		return body;
	}
	
	/* Again, this main method is provided to assist
	 * with testing. 
	 */
	public static void main(String[] args) {// Not needed, purely for testing. Simply prints the six phases of the man.
		Man m = new Man();
		for(int i=0; i<Man.MAX_INCORRECT; i++) {
			m.hang();
			System.out.println(m);
		}
	}
	
}

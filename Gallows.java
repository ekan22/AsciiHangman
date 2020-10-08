import java.util.Arrays;

public class Gallows {
	
	/* Gallows look like this:
	 *    ____
	 *   |    |
	 *   |    O 
	 *   |   \|/
	 *   |   / \
	 * __|__
	 */
	
	private Man man; //Instance Variables
	private char[] frame;

	public Gallows() { //Constructor Method
		this.man = new Man(); //Creates a new man object
		this.frame = new char[60]; //Using an array, creates an empty 6 by 9 panel of spaces to be filled in
		for (int i=0; i<frame.length;i++) {
			this.frame[i]=' ';
	}
		    for (int i = 0; i < 6; ) {
		      this.frame[i * 10] = '\n';
		      i++;
		    } 
		makeBase(); //These four methods create the base of the hangman structure
		makeCenterPost();
		makeBeam();
		makeRope();
	}
			
		
				
	
	
	public void makeCenterPost() { //Creates center post
		for(int i=1;i<6;i++) {
			this.frame[i*10+3]='|';
			
		}
	}
	
	public void makeBeam() { //Creates the upper bar or beam
		 for(int i =4;i<=7;i++) {
			 this.frame[i]='_';
		 }
		
	}
	
	public void makeBase() { //Makes the base of the structure
		for(int i = 51; i<=55; i++) {
			this.frame[i]='_';
		}
	}
	
	public void makeRope() {//Makes the "rope"
		this.frame[18]='|';
	}
	
	public void hang() { //Calls on the Hang method in the Man class
		this.man.hang();
	
	}
	
	public boolean isAlive() { //Calls on the isAlive method in the Man class
		return(this.man.isAlive());
	}
		
	public String toString() { //Converts the array to a string that can be easily printed, returns a string
	char[] manArray = this.man.toCharArray(); //Calls on the toCharArray method in the Man class
		String s = "";
		for (int i = 0;i<6;i++) {
			for(int j = 0; j<10;j++) {
				if(i>1&&i<5&&j>6) {
					s+=manArray[(i - 2) * 4 + j - 7]; //fills the String with the man class structure
		        } else {
				s+=frame[i*10+j];	//adds onto the String the Gallows base structure
			}	
			}
		}
		
		return s;
	}

	/* This code is included to allow you to test the
	 * Gallows independently from the Hangman code. 
	 */
	public static void main(String[] args) { //Not needed, purely for testing
		Gallows g = new Gallows();
		System.out.println(g);
		for(int i=0; i< Man.MAX_INCORRECT; i++) {
			g.hang();
			System.out.println(g);
		}
	}
}

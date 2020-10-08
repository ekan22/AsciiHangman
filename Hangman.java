/* Thought Questions #1: In java, when you take any number greater or equal to zero and you divide it by seven, then you multiply it by seven, you get the greatest multiple of 7 for your chosen number. 
For example, if your number was 22, and you divided it by 7, you would get three, then upon multiplying by 7, you get 21, which is the greatest multiple of 7 that is less than your chosen number, 22. When you subtract this
greatest multiple from your original number, you actually just get the remainder of your number divided by 7. In our previous example, if we subtracted our greatest multiple, 21, from our number, 22, we get one. 
This is the same as the remainder of 22 divided by 7. Thus, in the equation (a%7)==(a-a/7*7), since we know that (a%7) simply represents the remainder of your chosen number divided by seven, both sides are simply equal 
to the remainder of "a" divided by 7. Thus, this equation is true for a>=0.

Question #2: After finding the the adjusted month, the day of the month, the year, and the year divided by four, we need to find the remainder of these respective values divided by seven. Then, we add our newly found
four values up and subtract one from the sum. This will simply make Sunday correspond with a remainder of zero and make Sunday the first day of the week, rather than Saturday. For example, if we are looking at the
birthday of Tiger woods, his adjusted month is 6, his day was 30, year was 75, and year divided by four was 18. Then, the remainder of each of those values divided by seven is 6, 2, 5, and 4 respectively. 
Added together, the sum is 17. Now we subtract one to make Sunday the first day of the week. Now the sum is 16. Take the remainder after dividing by seven, and which is 2, corresponding expectedly with Tuesday, 
except Sunday is now the first day of the week, rather than the dreaded Saturday.

Question #3: On regular years, the first day of the month shifts right by one day each year. For example, March 1st, 2002 is a Friday, but the same day in 2003 is a Saturday. On leap years, however, the first day of each month 
shifts right by two days, rather than the normal one day. For example, March 1st, 2004 is a Monday, rather than the expected Sunday. Our algorithm takes this into account when it adds the whole number quotient of the year divided 
by four. However, it assumes that on a leap year, there is one extra day starting from January 1st. This is not true, as the extra day does not happen until February 29th. Thus, we must subtract one from our adjustments in 
January and February to counteract our algorithm's incorrect assumption. 
 */

import java.io.Console;
import java.util.Scanner;

public class Hangman {
private static char[] letters;	//Word that user is guessing
private static int lettersLeftToGuess; //Tracks how many letters the user has left to guess
private static char[] displayLetters; //Displays the user's correct guesses as well as underscores for letters left to guess
	public static void main(String[] args) {
		System.out.println("Welcome to the ASCII Version of Hangman!");		
		Console c = System.console();
		Scanner s = new Scanner(System.in);
		String prompt = "Please enter a secret word: ";
		if(c != null) {
			letters = c.readPassword(prompt, new Object[0]);
			for(int i=0; i<letters.length; i++) {
				letters[i] = Character.toUpperCase(letters[i]); //At this point, there is a secret word stored in all caps
			}
		} else {
			System.out.println("For best results, please run this from the command line."); //This is a fail-safe for ecllipse, where it cannot run readPassword correctly
			System.out.print(prompt);
			letters = s.nextLine().trim().toUpperCase().toCharArray();
			for(int i=0; i<10000; i++) System.out.println(); //Creates huge gap to prevent cheating
		}
		
		initialize(); //Initializes Variables through initialize method
		Gallows g = new Gallows(); //Creates new Gallows Object

		System.out.println(g); // Prints a Hangman base structure before any guesses through the Gallows class
		while(g.isAlive()&&lettersLeftToGuess>0) { //Checks to make sure that the user hasn't lost nor won
			System.out.println("Puzzle to solve: " + toString(displayLetters)); //Displays user's progress
			
			System.out.print("Please guess a letter: ");//Prompt
			char guess = s.nextLine().toUpperCase().charAt(0); //Only takes the first character of the users guess and stores it as a capital letter
			System.out.println(guess);
			if(!checkGuess(guess)) { //Calls on the checkGuess method, which returns true or false
			g.hang(); //Hangs the man if user's guess is wrong
			System.out.println(g); //Displays Hangman structure only if the user's guess is wrong
			}
		}
		System.out.println(Message()); //Reports which player has won
	}

	public static boolean checkGuess(char guess) { //Returns true or false
		boolean foundLetter=false;
		for(int i =0; i<letters.length;i++) { //Checks every letter of the word that is being guessed
			if (guess==letters[i]&& displayLetters[i*2]!=guess) { //Makes sure that the letter guessed is in the word and that it has not already been correctly guessed
				lettersLeftToGuess--;
				displayLetters[i*2]=guess; //replaces underscore with correct letter
				foundLetter=true;
		}
	}
		for(int i =0; i<letters.length;i++) { //Checks every letter of the word that is being guessed
			if (guess==letters[i]) { //Makes sure that the letter guessed is in the word. This makes sure that the user is neither punished nor rewarded for guessing the same correct letter twice.
				foundLetter=true;
			}
		} 
		return foundLetter;
	}
	
	public static String Message() { //Returns message indicating victor
		if(lettersLeftToGuess==0) { //Indicates that all letters have been correctly guessed
			return ("Success!  Player 2 wins!");
		}else {
			return("Game over! Player 1 wins!");
		}	
	}
	
	public static void initialize() { //Initializes Variable
		Gallows g = new Gallows();//New Gallows Object
		lettersLeftToGuess = letters.length;
		displayLetters = new char[2*letters.length]; //Creates a set of underscores and spaces the same length as the word
		for (int i=0; i<displayLetters.length;i+=2) { //It's i+=2 to account for the spaces between underscores
			displayLetters[i]='_';
		}
		for(int i=1; i<displayLetters.length;i+=2) {
			displayLetters[i]=' ';
		}
	}
		
		public static String toString(char[] display) { //This method converts a char array (displayLetters) into a String to be printed
			String s="";
			for (int i=0; i<display.length;i++) { //Adds each character of the array onto the String
				s+=display[i];
			}
			return s;
		}		
	}

	
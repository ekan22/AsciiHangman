import java.io.Console;//This extension class allows the user to choose a mode of play, additionally, in multiplayer mode, it keeps score.
import java.util.Scanner;

public class Extension {
private static char[] letters;	//Word that user or AI is guessing 
protected static int lettersLeftToGuess; //Letters left for user or AI to guess
private static char[] displayLetters; //Displays the users or AI's guesses as well as underscores for letters left to guess
private static int player2count; //Tracks the number of games player two has won
private static int player1count;//Tracks the number of games player one has won
private static int totalcount;//Tracks the total number of games played
private static int yes;//Lets user decide whether or not they want to play another game
private static int playagain;
private static String[] fruit = new String[] {"STRAWBERRY", "APPLE", "GRAPE", "BANANA", "APRICOT", "BLUEBERRY", "STARFRUIT", "GRAPEFRUIT"};//Wordbox for fruits
private static String[] country = new String[] {"AMERICA", "MEXICO", "CHINA", "RUSSIA", "FRANCE", "ENGLAND", "CANADA", "THAILAND"};//Wordbox for countries
private static String[] sport = new String[] {"LACROSSE", "BASEBALL", "SOCCER", "BASKETBALL", "VOLLEYBALL", "FOOTBALL", "CREW", "SQUASH"};//Wordbox for fruits
	public static void main(String[] args) {
		System.out.println("Welcome to the ASCII Version of Hangman!");
		System.out.print("Please choose your game mode by typing 1 for multiplayer, 2 for AI guessing, 3 for single-player, and 4 for Smart AI guessing: ");	//User chooses their game mode	
		Console c = System.console();
		Scanner s = new Scanner(System.in);
		Scanner z = new Scanner(System.in);
		int choice = z.nextInt();
		if (choice == 1) { //Multiplayer mode
			player2count=0;//Sets all tracking variables to 0
			player1count=0;
			totalcount=0;
			int playagain=1;//Starts game
			
				while(playagain==1) { //Continues game until user ends the game
			System.out.println("You are playing multiplayer mode");				
			System.out.println("Player 1 has won "+player1count+" games");//Displays score
			System.out.println("Player 2 has won "+player2count+" games");
		String prompt = "Please enter a secret word: ";
		if(c != null) {
			letters = c.readPassword(prompt, new String[0]);
			for(int i=0; i<letters.length; i++) {
				letters[i] = Character.toUpperCase(letters[i]); //At this point, there is a secret word stored in all caps
			}
		} else {
			System.out.println("For best results, please run this from the command line."); //Fail safe code for eclipse, which can't run readPassword properly
			System.out.print(prompt);
			letters = s.nextLine().trim().toUpperCase().toCharArray();
			for(int i=0; i<10000; i++) System.out.println();
		}
		
		initialize();//Initializes variables
		Gallows g = new Gallows();//Creates new gallows object

		System.out.println(g);//Prints base structure
		while(g.isAlive()&&lettersLeftToGuess>0) { //Checks that the game has neither been won nor lost
			System.out.println("Puzzle to solve: " + toString(displayLetters));//Prints the puzzle that is currently being solved
			
			System.out.print("Please guess a letter: "); //Takes in a guess from the player
			Scanner d = new Scanner(System.in);
			char guess = d.nextLine().toUpperCase().charAt(0);//Takes only the first character and capitalizes it
			System.out.println(guess);
			if(!checkGuess(guess)) {//Calls on checkGuess method, which returns true or false
			g.hang();//Hangs the man if incorrect
			System.out.println(g);//Prints the Hangman if incorrect
			}
		}
		totalcount++;//Increases the total number of games played
		if(totalcount%2==1) {//Accounts for the fact that the players take turns guessing and choosing the word
		if(lettersLeftToGuess==0) { //Adjusts the score based on who won
			System.out.println("Congrats, player 2 has won!");
			player2count++; 
		}else {
			System.out.println("Oops, you let player 1 win.");
			player1count++;
		}
			
		}else {
			if(lettersLeftToGuess==0) { //Adjusts the score based on who won
				System.out.println("Congrats, player 1 has won!");
				player1count++;
			}else {
				System.out.println("Oops, you let player 2 win.");
				player2count++;
			}
		}
		System.out.println("Player 1 has won "+player1count+" games");//Displays score
		System.out.println("Player 2 has won "+player2count+" games");	
		System.out.println("Would you like the keep playing?");// Asks users if they want to keep playing
		System.out.println("Press 1 for yes, press 2 for no.");
		Scanner t = new Scanner(System.in);//Takes in user's answer
		yes = t.nextInt();
		if (yes==2) {//This and the following line exit the while loop, ending the game
			playagain=2;
		System.out.println("Thanks for playing. Here are the final scores: "); //Displays final score
		System.out.println("Player 1 has won "+player1count+" games");
		System.out.println("Player 2 has won "+player2count+" games");		
			}
		}
	}
		else if(choice ==2) { //Basic AI mode
			System.out.println("You are playing against an AI"); 
			String prompt = "Please enter a secret word for AI to solve: "; //User chooses word for AI to solve
			if(c != null) {
				letters = c.readPassword(prompt, new String[0]);
				for(int i=0; i<letters.length; i++) {
					letters[i] = Character.toUpperCase(letters[i]);  //At this point, there is a secret word stored in all caps
				}
			} else {
				System.out.println("For best results, please run this from the command line."); //Fail safe code for eclipse, which can't run readPassword properly
				System.out.print(prompt);
				letters = s.nextLine().trim().toUpperCase().toCharArray();
				for(int i=0; i<10000; i++) System.out.println();
			}
			initialize(); //Initializes Variables
			Gallows g = new Gallows(); //Creates new Gallows object
			Ai comp = new Ai(); //new AI object

			System.out.println(g); //Prints base
			while(g.isAlive()&&lettersLeftToGuess>0) {//Makes sure that the game has neither been won nor lost by the AI
				System.out.println("Puzzle for AI to solve: " + toString(displayLetters)); //Displays the puzzle currently being solved by the AI
				char compGuess = comp.aiGuess(); //This is the letter guessed by the AI
				System.out.println(compGuess);
				if(!checkGuess(compGuess)) { //Checks if the AI guess is correct
					g.hang();// Hangs if incorrect
					System.out.println(g); //Prints hangman if incorrect
				}
			}
			System.out.println(comp.displayAIMessage()); //Prints message indicating how won using method from the AI class
		}
		else if(choice ==3) { //Single Player Mode
	
			System.out.println("You are playing single player mode");
			System.out.println("Please select genre of words:"); //Allows user to select genre of words to guess
			System.out.println("Type 1 for fruits, 2 for countries, and 3 for sports");//Takes in input from user
			Scanner choose = new Scanner(System.in);
			int p = choose.nextInt();
			Gallows g=new Gallows(); //New Gallows object
			
			if(p==1) { //Player chose fruit genre
				letters = fruit[(int) (Math.random()*fruit.length)].toCharArray(); //Computer chooses random word for user to guess from fruit wordbox
				initialize(); //Initializes variables
				System.out.println(g);//Prints base hangman structure
				while(g.isAlive()&&lettersLeftToGuess>0) { //Checks that the game has neither been won nor lost
					System.out.println("Puzzle to solve: " + toString(displayLetters));//Prints the puzzle that is currently being solved
					
					System.out.print("Please guess a letter: "); //Takes in a guess from the player
					Scanner d = new Scanner(System.in);
					char guess = d.nextLine().toUpperCase().charAt(0);//Takes only the first character and capitalizes it
					System.out.println(guess);
						if(!checkGuess(guess)) {//Calls on checkGuess method, which returns true or false
							g.hang();//Hangs the man if wrong
							System.out.println(g);//Prints the hangman if wrong
					}
				}
				if(lettersLeftToGuess==0) { //Displays who won the game
					System.out.println("You won!"); 
				}else {
					System.out.println("Oops, you lost.");
				}	
				
			}else if(p==2) { //Player chose country genre
				letters = country[(int) (Math.random()*country.length)].toCharArray(); //Computer chooses random word for user to guess from country wordbox
				initialize();//Initializes variables
				System.out.println(g);//Prints base hangman structure
				while(g.isAlive()&&lettersLeftToGuess>0) { //Checks that the game has neither been won nor lost
					System.out.println("Puzzle to solve: " + toString(displayLetters));//Prints the puzzle that is currently being solved
					
					System.out.print("Please guess a letter: "); //Takes in a guess from the player
					Scanner d = new Scanner(System.in);
					char guess = d.nextLine().toUpperCase().charAt(0);//Takes only the first character and capitalizes it
					System.out.println(guess);
					if(!checkGuess(guess)) {//Calls on checkGuess method, which returns true or false
						g.hang();//Hangs the man if wrong
						System.out.println(g);//Prints the hangman if wrong
					}
				}
				if(lettersLeftToGuess==0) { //Displays who won the game
					System.out.println("You won!"); 
				}else {
					System.out.println("Oops, you lost.");
				}	

			}else if(p==3) { //Player chose sports genre
				letters = sport[(int) (Math.random()*sport.length)].toCharArray();//Computer chooses random word for user to guess from sports wordbox
				initialize();//Initializes variables
				System.out.println(g);//Prints base hangman structure
				while(g.isAlive()&&lettersLeftToGuess>0) { //Checks that the game has neither been won nor lost
					System.out.println("Puzzle to solve: " + toString(displayLetters));//Prints the puzzle that is currently being solved
					
					System.out.print("Please guess a letter: "); //Takes in a guess from the player
					Scanner d = new Scanner(System.in);
					char guess = d.nextLine().toUpperCase().charAt(0);//Takes only the first character and capitalizes it
					System.out.println(guess);
					if(!checkGuess(guess)) {//Calls on checkGuess method, which returns true or false
					g.hang();//Hangs the man if wrong
					System.out.println(g);//Prints the hangman if wrong
					}
				}
				if(lettersLeftToGuess==0) { //Prints out who won
					System.out.println("You won!"); 
				}else {
					System.out.println("Oops, you lost.");
			}	
		}

	}
			
		else if(choice ==4) { //Smart AI Mode
			System.out.println("You are playing against a Smart AI"); 
			String prompt = "Please enter a secret word for AI to solve: "; //User chooses word for AI to solve
			if(c != null) {
				letters = c.readPassword(prompt, new String[0]);
				for(int i=0; i<letters.length; i++) {
					letters[i] = Character.toUpperCase(letters[i]); //At this point, there is a secret word stored in all caps
				}
			} else {
				System.out.println("For best results, please run this from the command line.");  //Fail safe code for eclipse, which can't run readPassword properly
				System.out.print(prompt);
				letters = s.nextLine().trim().toUpperCase().toCharArray();
				for(int i=0; i<10000; i++) System.out.println();
			}
			initialize(); //Initializes variables
			Gallows g = new Gallows();//new Gallows object
			Ai comp = new Ai();//New AI object
			
			System.out.println(g);//Prints base
			while(g.isAlive()&&lettersLeftToGuess>0) {//Makes sure that the game has neither been won nor lost
				System.out.println("Puzzle for AI to solve: " + toString(displayLetters)); //Displays the puzzle currently being solved
				char strongCompGuess = comp.strongAiGuess(); //This is the letter guessed by the AI
				System.out.println(strongCompGuess); 
				if(!checkGuess(strongCompGuess)) { //Checks if the guess is correct
					g.hang();//Hangs man if incorrect
					System.out.println(g);//Prints hangman if incorrect
				}
			}
			System.out.println(comp.displayAIMessage());//Prints message indicating victor
		}
	}

	public static boolean checkGuess(char guess) { //returns true or false
		boolean foundLetter=false;
		for(int i =0; i<letters.length;i++) { //checks every letter of the word
			if (guess==letters[i] && displayLetters[i*2]!=guess) {//Makes sure that the letter guessed is in the word and that it has not already been correctly guessed
			lettersLeftToGuess--;
			displayLetters[i*2]=guess; //replaces underscore with correct letter
			foundLetter=true;
			}
		} 
		for(int i =0; i<letters.length;i++) { //Checks every letter of the word that is being guessed
			if (guess==letters[i]) { //Makes sure that the letter guessed is in the word. This makes sure that the guesser is neither punished nor rewarded for guessing the same correct letter twice.
				foundLetter=true;
			}
		} 
		return foundLetter;
		
	
	}

		public static void initialize() { //Initializes variables
			Gallows g = new Gallows();
			lettersLeftToGuess = letters.length;
			displayLetters = new char[2*letters.length]; //Creates a set of underscores and spaces the same length as the word
			for (int i=0; i<displayLetters.length;i+=2) { //It's i+=2 to account for the spaces between underscores
				displayLetters[i]='_';
		}
			for(int i=1; i<displayLetters.length;i+=2) {
				displayLetters[i]=' ';
		}
	}
		public static String toString(char[] display) {//This method converts the display method array into a String to be printed
			String s="";
			for (int i=0; i<display.length;i++) {//Adds each character of the array onto the String
				s+=display[i];
		}
			return s;
		}		
	}

	
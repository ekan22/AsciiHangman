import java.util.Random; //This class has two uses, one is for the regular AI mode, the other is for the smart AI mode
public class Ai {
private String possibleLetters;
private Extension extension;
protected char[] guess;
private String strongPossibleLetters;
protected char[] strongGuess;

public Ai() {
	this.extension = new Extension();//Calls on Extension class
	possibleLetters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";//For regular AI mode, creates reference String
	strongPossibleLetters = ""; //The following if statements create the reference String for the smart AI mode based on the probability of their appearance in English words
	for (int i = 1;i<=55;i++) {
		strongPossibleLetters+="E";
	}
	for (int i =1;i<=43;i++) {
		strongPossibleLetters+="A";
	}
	for (int i = 1;i<=38;i++) {
		strongPossibleLetters+="R";
	}
	for (int i = 1;i<=38;i++) {
		strongPossibleLetters+="I";
	}
	for (int i = 1;i<=35;i++) {
		strongPossibleLetters+="O";
	}
	for (int i = 1;i<=35;i++) {
		strongPossibleLetters+="T";
	}
	for (int i = 1;i<=33;i++) {
		strongPossibleLetters+="N";
	}
	for (int i = 1;i<=29;i++) {
		strongPossibleLetters+="S";
	}
	for (int i = 1;i<=28;i++) {
		strongPossibleLetters+="L";
	}
	for (int i = 1;i<=23;i++) {
		strongPossibleLetters+="C";
	}
	for (int i = 1;i<=18;i++) {
		strongPossibleLetters+="U";
	}
	for (int i = 1;i<=17;i++) {
		strongPossibleLetters+="D";
	}
	for (int i = 1;i<=16;i++) {
		strongPossibleLetters+="P";
	}
	for (int i = 1;i<=15;i++) {
		strongPossibleLetters+="M";
	}
	for (int i = 1;i<=15;i++) {
		strongPossibleLetters+="H";
	}
	for (int i = 1;i<=13;i++) {
		strongPossibleLetters+="G";
	}
	for (int i = 1;i<=10;i++) {
		strongPossibleLetters+="B";
	}
	for (int i = 1;i<=9;i++) {
		strongPossibleLetters+="F";
	}
	for (int i = 1;i<=9;i++) {
		strongPossibleLetters+="Y";
	}
	for (int i = 1;i<=7;i++) {
		strongPossibleLetters+="W";
	}
	for (int i = 1;i<=6;i++) {
		strongPossibleLetters+="K";
	}
	for (int i = 1;i<=5;i++) {
		strongPossibleLetters+="V";
	}
	strongPossibleLetters+="X";
	strongPossibleLetters+="Z";
	strongPossibleLetters+="J";
	strongPossibleLetters+="Q";
}

public char aiGuess() { //The computer randomly picks a letter to guess from the reference string
	Random rand = new Random();
	char aiguess = possibleLetters.charAt(rand.nextInt(26));
return aiguess;

}
public String displayAIMessage() { //Displays message indicating the victor for both AI modes
	if (Extension.lettersLeftToGuess==0) {
		return "AI won!";
	}else {
		return "You won!";
	}
}
public char strongAiGuess() { //For Smart AI, the computer randomly picks a letter from the modified string to guess
	Random rand = new Random();
	char strongGuess = strongPossibleLetters.charAt(rand.nextInt(501));
return strongGuess;

}
}

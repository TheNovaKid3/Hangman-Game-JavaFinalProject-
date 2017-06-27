import java.util.Scanner;
import javax.swing.*;

class Hangman {
	public static void main(String[] args) {
		int mistakes = 0;
		int e = 0;
		int y = 0;
		String solution = " ";
		String allGuess = " ";
		String solutionBlank = " ";
		Scanner inputDevice = new Scanner(System.in);
		String guess;
		int a = 0;
		System.out.println("If you would like to play input 1.");
		a = inputDevice.nextInt();
		inputDevice.nextLine();
		System.out.println("------------------------------------------------------------------------------");
		while (a > 0) {
			e = 0;
			int winLoseCondition = 0;
			solution = JOptionPane.showInputDialog(null, "Input word or phrase.");
			String[] solutionArray = new String[solution.length()];
			for (int x = 0; x < solution.length(); x++) {
				solutionArray[x] = String.valueOf(solution.charAt(x));
			}
			displayImage(mistakes);
			solutionBlank = solutionBlanking(solutionArray, solution, allGuess);
			System.out.println(solutionBlank);
			System.out.println("------------------------------------------------------------------------------");
			while (winLoseCondition == 0) {
				y = 0;
				while (y == 0) {
					guess = getGuess();
					System.out.println("------------------------------------------------------------------------------");
					boolean alreadyGuessed = false;
					boolean checkGuess = false;
					alreadyGuessed = allGuess.contains(guess);
					if (alreadyGuessed == false){
						guess = guess.toUpperCase();
						alreadyGuessed = allGuess.contains(guess);
					}
					if (alreadyGuessed == false){
						guess = guess.toLowerCase();
						alreadyGuessed = allGuess.contains(guess);
					}
					if (alreadyGuessed == true){
						System.out.println("You have already guessed that letter.");
					}
					else {
						checkGuess = solution.contains(guess);
						if (checkGuess == false){
							guess = guess.toUpperCase();
							checkGuess = solution.contains(guess);
						}
						if (checkGuess == false){
							guess = guess.toLowerCase();
							checkGuess = solution.contains(guess);
						}
						if (checkGuess == true) {
							System.out.println("The Word does contain " + guess + ".");				
							allGuess = allGuess + guess.toLowerCase();	
							y++;
						}
						else {
							System.out.println("The Word does not contain " + guess + ".");
							allGuess = allGuess + guess.toLowerCase();
							mistakes++;
							y++;
						}
					}
				}
				displayImage(mistakes);
				solutionBlank = solutionBlanking(solutionArray, solution, allGuess);
				System.out.println(solutionBlank);
				System.out.println("You have already guessed these letters: " + allGuess);
				System.out.println("------------------------------------------------------------------------------");
				if (mistakes == 6) {
					winLoseCondition--;
				}
				if (solutionBlank == solution) {
					winLoseCondition++;
				}
			}
			if (winLoseCondition < 0) {
				System.out.println("You lost. The correct solution is '" + solution + ".'");
			}
			if(winLoseCondition > 0){
				System.out.println("You Won. HURRAY!!");
			}
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("If you would like to quit input a -1. If you would like to play agian input 1.");
			a = inputDevice.nextInt();
			inputDevice.nextLine();
			allGuess = " ";
		}
		return;
	}
	public static String getGuess(){
		String guess = " ";
		String nonsense = " ";
		Scanner inputDevice = new Scanner(System.in);
		System.out.print("Guess >> ");
		guess = inputDevice.nextLine();
		guess = guess.substring(0, 1);
		return guess;
		}
	public static void displayImage(int mistakes){
		HangmanImage game = new HangmanImage();
		switch (mistakes) {
			case 0:
				game.zeroMistakes();
				break;
			case 1:
				game.oneMistake();
				break;
			case 2:
				game.twoMistakes();
				break;
			case 3:
				game.threeMistakes();
				break;
			case 4:
				game.fourMistakes();
				break;
			case 5:
				game.fiveMistakes();
				break;
			case 6:
				game.sixMistakes();
				break;
		}
	}
	public static String solutionBlanking(String[] solutionArray, String solution, String allGuess){
		boolean alreadyGuessed = false;
		String solutionBlank = solution;
		for (int z = 0; z < solution.length(); z++) {
			String b = solutionArray[z];
			alreadyGuessed = allGuess.contains(b);
			if (alreadyGuessed == false){
				b = b.toUpperCase();
				alreadyGuessed = allGuess.contains(b);
			}
			if (alreadyGuessed == false){
				b = b.toLowerCase();
				alreadyGuessed = allGuess.contains(b);
			}
			if (alreadyGuessed == false) {
				if (b == " ") {
					solutionBlank = solutionBlank.replace(" ", " ");
				}
				else {
					solutionBlank = solutionBlank.replace(solutionArray[z], "_");
				}
			}
		}
		return solutionBlank;
	}
}
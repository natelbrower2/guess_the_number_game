import java.util.Scanner;

public class Game {
    public int _level;
    public int _guessAttempts;
    public int _randomNumRange;
    public int _randomNum;

    public Game(int level, int guessAttempts, int randomNumRange) {
        _level = level;
        _guessAttempts = guessAttempts;
        _randomNumRange = randomNumRange;
    }

    public void generateRandomNumber() {
        _randomNum = (int)(Math.random() * _randomNumRange);
    }

    public int start() {
        // define variables for the game
        generateRandomNumber();
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        String input;
        int numberGuessed;
        int guessesRemaining = _guessAttempts;
        
        // Tell the user what range of numbers that the random number can be between
        System.out.println("\nLevel " + _level);
        System.out.println("The number you need to guess is between 0 and " + _randomNumRange);
        System.out.println("You have " + _guessAttempts + " guesses");

        // Main game loop
        while (run) {

            // Ask the user for their guess
            System.out.print("What is your guess? ");
            input = scanner.nextLine();

            // try to turn the input into an int
            try {
                numberGuessed = Integer.parseInt(input);
            }
            catch(NumberFormatException e) {
                // jump back to the top of the main game loop if the input was invalid
                System.out.println("invalid input, please try again");
                continue;
            }

            // decrement guesses remaining
            guessesRemaining -= 1;
            
            // probmpt the user towards the correct number they need to guess
            if (numberGuessed == _randomNum) { // the user guessed the number, end the while loop
                System.out.println("You guessed it in " + (_guessAttempts - guessesRemaining) + " guesses! The number was " + _randomNum + "\n");
                run = false;
                break;
            }
            else if (numberGuessed < _randomNum) { // the users guess was too low
                System.out.println("Your guess was too low");
            }
            else { // the users guess was too high
                System.out.println("Your guess was too high");
            }

            // update the guesses remaining variable and let the user know how many guesses they have left
            if (guessesRemaining  == 0) {
                // end the game if the user has run out of guesses
                run = false;
                System.out.println("You have no guesses remaining. The number was " + _randomNum);
            }
            else{
                System.out.println();
                System.out.println(guessesRemaining + " Guesses Remaining\n");
            }
        }

        // return how many guesses the user took
        return _guessAttempts - guessesRemaining;
    }
}
import java.util.Scanner;

public class Main {
    public static int level = 0;
    public static int guessAttempts = 4;
    public static int randomNumRange = 5;
    public static int[] highScores = Filer.getHighScores();

    public static void displayHighScores() {
        System.out.println();
        for (int i = 0; i < 10; i++) {
            if (highScores[i] == 9) {
                break;
            }
            System.out.println("Level " + (i + 1) + " high score = " + highScores[i] + " guesses");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        String input;
        int userScore;

        System.out.println("Welcome to the guess the number game!");
        while (run) {
            // Display the main menue options
            System.out.println("Main Menue:");
            System.out.println("    1. New game");
            System.out.println("    2. Next Level");
            System.out.println("    3. Score Board");
            System.out.println("    4. Quit");
            System.out.print("What is your selection: ");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    // start a new game
                    System.out.println("Starting new game...");
                    level = 1;
                    guessAttempts = 5;
                    randomNumRange = 10;
                    userScore = new Game(level, guessAttempts, randomNumRange).start();

                    // if the users score is better than the previous score, update the high scores file
                    if (userScore < highScores[level - 1]){
                        highScores[level - 1] = userScore;
                    }
                    Filer.updateHighScore(highScores);
                    break;
                case "2":
                    // Play next level
                    level += 1;
                    guessAttempts += 1;
                    randomNumRange *= 2;
                    if (guessAttempts > 8) {
                        guessAttempts = 8; // keep the number of guesses allowed under 9
                    }

                    // Check if the user has already beaten all 10 levels
                    if (level > 10) {
                        System.out.println("You have completed all 10 levels! Congradulations");
                    }
                    else {
                        // start the next level
                        userScore = new Game(level, guessAttempts, randomNumRange).start();
                        // if the users score is better than the previous score, update the high scores file
                        if (userScore < highScores[level - 1]){
                            highScores[level - 1] = userScore;
                        }
                        Filer.updateHighScore(highScores);
                    }
                    break;
                case "3":
                    // display the score board
                    displayHighScores();
                    break;
                default:
                    run = false;
            }
        }
        System.out.println("Thanks for playing.");
        scanner.close();
    }
}
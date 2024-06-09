import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Filer {
  public static String fileName = "high_score.txt";

  public static void updateHighScore(int[] scores) {
    try {
        FileWriter fileWriter = new FileWriter(fileName);
        for (int i : scores) { // write each score in scores to the high_score file
          fileWriter.write(i + "\n");
        }
        fileWriter.close();
    } catch (IOException e) { // catch any errors
        System.out.println("An error occurred.");
        e.printStackTrace();
    }
  }

  public static int[] getHighScores() {
    // char[] highScores = {'0','0','0','0','0','0','0','0','0','0'};
    int[] highScores = {9,9,9,9,9,9,9,9,9,9}; // set high scores to a high number as place holders
    int i = 0;
    try {
      File file = new File(fileName);
      Scanner myReader = new Scanner(file);
      while (myReader.hasNextLine()) { // for each item in the high_score file, update the highScores array to its correct value
        String data = myReader.nextLine();
        highScores[i] = data.charAt(0) - 48; // subtract 48 to turn the ascii value into an int
        i += 1;
      }
      myReader.close();
    } catch (FileNotFoundException e) { // catch any errors
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    return highScores;
  }
}


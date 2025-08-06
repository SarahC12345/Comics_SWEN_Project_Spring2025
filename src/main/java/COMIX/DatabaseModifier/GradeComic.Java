package COMIX.DatabaseModifier;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import COMIX.ComicGrader.Comic;
import COMIX.ComicGrader.GradedComic;

public class GradeComic implements CommandInvoker{
    Comic comic;
    int grade;

    public GradeComic(Comic comic, int grade) {
        this.comic = comic;
        this.grade = grade;
    }

    @Override
    public void execute() {
      try {
        File inputFile = new File("src/main/java/COMIX/data/personalcomics.csv");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        StringBuilder inputBuffer = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.contains(comic.toString())) {
                // Replace the ungraded comic line with the graded comic line
                GradedComic gradedComic = new GradedComic(grade, comic);
                inputBuffer.append(gradedComic.toString());  // assuming toString() includes grade info
            } else {
                // Leave other lines untouched
                inputBuffer.append(line);
            }
            inputBuffer.append(System.lineSeparator());
        }
        reader.close();

        // Write the modified content back to the file
        FileOutputStream fileOut = new FileOutputStream(inputFile);
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

    } catch (IOException e) {
        e.printStackTrace();
    }
      
    }

    @Override 
    public void undo() {
    }


}

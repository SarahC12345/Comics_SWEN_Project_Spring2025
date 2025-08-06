package COMIX.DatabaseModifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import COMIX.ComicGrader.Comic;

public class EditComic implements CommandInvoker{

    private Comic comic;
    private String oldValue;
    private String newValue;

    public EditComic(Comic comic, String oldValue, String newValue){
        this.comic = comic;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public void execute(){
        // Edit the comic in the personalcomics.csv file
        try {
            File inputFile = new File("src/main/java/COMIX/data/personalcomics.csv");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            StringBuilder inputBuffer = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
            // Unknown if this line properly checks for containing comic
            if (line.contains(comic.toString())) {
                // Edit the field of the comic
                String[] fields = line.split(",");
                for (int i = 0; i < fields.length; i++) {
                    if (fields[i].equals(oldValue)) {
                        fields[i] = newValue;
                    }
                }
                // Reconstruct the line
                StringBuilder newLine = new StringBuilder();
                for (int i = 0; i < fields.length; i++) {
                    newLine.append(fields[i]);
                    if (i != fields.length - 1) {
                        newLine.append(",");
                    }
                } 
                inputBuffer.append(newLine.toString());
                inputBuffer.append(System.lineSeparator());
            } else {
                inputBuffer.append(line);
                inputBuffer.append(System.lineSeparator());
            }
            }
            reader.close();

            // Write the updated content back to the file
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
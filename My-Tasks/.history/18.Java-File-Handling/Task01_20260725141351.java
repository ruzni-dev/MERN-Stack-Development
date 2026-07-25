// File Class

import java.io.File;
import java.io.IOException;

public class Task01 {
    public static void main(String[] args) {

        // Create a folder
        File folder = new File("StudentFiles");

        if (folder.mkdir()) {
            System.out.println("Folder created successfully.");
        } else {
            System.out.println("Folder already exists.");
        }

        // Create a file
        File file = new File("StudentFiles/student.txt");

        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error creating file.");
            e.printStackTrace();
        }
    }
}

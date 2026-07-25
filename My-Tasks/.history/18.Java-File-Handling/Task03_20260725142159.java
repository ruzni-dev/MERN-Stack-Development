//  Reading a File

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {
        try {
            // Create a File object
            File file = new File("StudentFiles/student.txt");

            // Create a Scanner object to read the file
            Scanner reader = new Scanner(file);

            System.out.println("Reading File Content");
            System.out.println("--------------------");

            // Read each line until the end of the file
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }

            // Close the Scanner
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
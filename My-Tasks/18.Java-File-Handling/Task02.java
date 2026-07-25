// FileWriter Class

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) {
        File file = new File("StudentFiles/student.txt");

        try {
            // Write data into file
            FileWriter writer = new FileWriter(file);

            writer.write("Java File Handling\n");
            writer.write("Name : Mohamad Ruzni\n");
            writer.write("Course : Java Programming\n");
            writer.write("Topic : FileWriter Class\n");
            writer.close();

            System.out.println("Data written successfully.\n");

            // Read file
            Scanner reader = new Scanner(file);
            System.out.println("File Contents");
            System.out.println("-------------");

            while (reader.hasNextLine()) {
                System.out.println(reader.nextLine());
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("File operation failed.");
            e.printStackTrace();
        }
    }
}
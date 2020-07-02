package unit.input;

import data.raw.process.input.RawDataSetFileImplementer;
import data.raw.process.input.RawUserQuestionInput;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class RawUserQuestionFileTest {

    @Test
    public static void processData() {
        // Files should be in project root folder (e.g location where POM exist)
        try {
            RawUserQuestionInput rawUserQuestionInput = new RawUserQuestionInput();

            Scanner scanner = new Scanner(System.in);
            String input = "";

            while (!"q".equalsIgnoreCase(input)) {
                System.out.println("Please enter the question : ");
                String question = scanner.nextLine(); // get string
                System.out.format("question: ", question);
                rawUserQuestionInput.processRawData(question);
                System.out.print("Enter something (q to quite): ");

                input = scanner.nextLine();
                System.out.println("input : " + input);
            }

            System.out.println("bye bye!");


          //  Assert.assertEquals(true, Files.exists(Paths.get("ComprehensionOutput_old.txt")));
        } catch (Exception e) {
          //  Assert.assertTrue(e instanceof IllegalArgumentException);
        }
    }

    public static void main(String[] args) {
        processData();
    }

}

package data.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileOperation {
    private static Logger logger = LoggerFactory.getLogger(FileOperation.class);

    /**
     * Read file and return String
     *
     * @param fileName
     * @return file content as String
     */
    public static String readFile(final String fileName) {
        String fileData = "";
        try {
            Path path = Paths.get(fileName);
            Stream<String> lines = Files.lines(path);
            fileData = lines.collect(Collectors.joining("\n"));
            lines.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Read from file exception");
        }
        return fileData.replaceAll("[\\n]", " ").trim();
    }

    /**
     * Write answers to the output file
     *
     * @param answers
     */
    public static void writeAnswerToFile(final List<String> answers) {
        StringBuilder response = new StringBuilder();
        answers.stream().forEach(answer -> response.append(answer + " \n"));
        try {
            Path outputFilePath = Paths.get("ComprehensionOutput.txt");
            Files.write(outputFilePath, response.toString().getBytes());
            logger.info("Output file written to location {} name ComprehensionOutput.txt", outputFilePath);
        } catch (IOException e) {
            throw new IllegalArgumentException("File write exception");
        }
    }

    private FileOperation() {
    }
}

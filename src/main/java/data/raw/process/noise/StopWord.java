package data.raw.process.noise;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StopWord {
    public String cleanStopWord(final String rawText) {
        List<String> stopWords = readStopWords();
        List<String> allWords = Stream.of(rawText.toLowerCase().split(" ")).collect(Collectors.toList());

        allWords.removeAll(stopWords);
        return allWords.stream().collect(Collectors.joining(" "));
    }

    public List<String> readStopWords() {
        List<String> stopWords = null;
        String stopWordFileName = "stopwords.txt";
        try {
            Path resourceFilePath = Paths.get(getClass().getClassLoader()
                    .getResource(stopWordFileName).toURI());
            stopWords = Files.readAllLines(resourceFilePath);
        } catch (IOException e) {
            throw new IllegalArgumentException("StopWord file cannot be read");
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("incorrect URL");
        }
        return stopWords;
    }
}

package unit.noise;

import data.raw.process.noise.NoiseTextProcess;
import data.raw.process.noise.StopWord;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StopWordsTest {

    @Test
    public void readStopWords() {
        StopWord stopWord = new StopWord();
        List<String> stopWords = stopWord.readStopWords();
        Assert.assertTrue(stopWords != null);
        Assert.assertTrue(stopWords.size() > 0);
    }

    @Test
    public void cleanStopWords() {
        String original = "The quick brown fox jumps over the lazy dog";
        String target = "quick brown fox jumps lazy dog";

        NoiseTextProcess noiseTextProcess = new NoiseTextProcess();
        String result = noiseTextProcess.cleanStopWordText(original);
        Assert.assertTrue(result.equalsIgnoreCase(target));
    }

    @Test
    public void cleanStopWordsComplexText() {
        String original = "The unique stripes of zebras make them one of the" +
                "animals most familiar to people. They occur in a variety of habitats," +
                "such as grasslands, savannas, woodlands, thorny scrublands," +
                "mountains, and coastal hills";
        String target = "unique stripes zebras make one theanimals most familiar people. occur variety habitats,such grasslands, savannas, woodlands, thorny scrublands,mountains, coastal hills";

        NoiseTextProcess noiseTextProcess = new NoiseTextProcess();
        String result = noiseTextProcess.cleanStopWordText(original);
        Assert.assertTrue(result.equalsIgnoreCase(target));
    }

    @Test
    public void cleanStopWordsPunctuation() {
        String original = "The * quick ~!@#$% brown %^&*( fox )((&&*(^jumps over }|{\": ?<<>the lazy dog";
        String target = " quick % brown % fox jumps  the lazy dog";

        NoiseTextProcess noiseTextProcess = new NoiseTextProcess();
        String cleanStopWordText = noiseTextProcess.cleanStopWordText(original);
        String result = noiseTextProcess.cleanPunctuation(cleanStopWordText);
        Assert.assertTrue(result.equalsIgnoreCase(target));
    }
}

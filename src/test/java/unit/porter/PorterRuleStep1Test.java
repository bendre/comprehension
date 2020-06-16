package unit.porter;

import org.junit.Assert;
import org.junit.Test;
import data.raw.process.stem.porter.PorterAlgorithm;

public class PorterRuleStep1Test {

    @Test
    public void porterStep1Test() {
        PorterAlgorithm porterAlgorithm = new PorterAlgorithm();
        String stemRawWords = porterAlgorithm.steamRawWords(getPorterStep1RawWords());
        assertPorterStep2RawWords(stemRawWords);
    }

    private String getPorterStep1RawWords() {
        //Input Raw Words
        StringBuilder rawWords = new StringBuilder();
        rawWords.append("caresses ");
        rawWords.append("ponies ");
        rawWords.append("ties ");
        rawWords.append("caress ");
        rawWords.append("cats ");
        rawWords.append("feed ");
        rawWords.append("agreed ");
        rawWords.append("plastered ");
        rawWords.append("bled ");
        rawWords.append("motoring ");
        rawWords.append("sing ");
        rawWords.append("troubled ");
        rawWords.append("sized ");
        rawWords.append("hopping ");
        rawWords.append("tanned ");
        rawWords.append("falling ");
        rawWords.append("hissing ");
        rawWords.append("fizzed ");
        rawWords.append("failing ");
        rawWords.append("filing ");
        rawWords.append("sky ");

        return rawWords.toString();
    }

    private void assertPorterStep2RawWords(String stemRawWords) {
        Assert.assertTrue(stemRawWords.contains("caress"));
        Assert.assertTrue(stemRawWords.contains("poni"));
        Assert.assertTrue(stemRawWords.contains("caress"));
        Assert.assertTrue(stemRawWords.contains("cat"));
        Assert.assertTrue(stemRawWords.contains("feed"));
        Assert.assertTrue(stemRawWords.contains("agree"));
        Assert.assertTrue(stemRawWords.contains("plaster"));
        Assert.assertTrue(stemRawWords.contains("bled"));
        Assert.assertTrue(stemRawWords.contains("sing"));
        Assert.assertTrue(stemRawWords.contains("trouble"));
        Assert.assertTrue(stemRawWords.contains("size"));
        Assert.assertTrue(stemRawWords.contains("hop"));
        Assert.assertTrue(stemRawWords.contains("tan"));
        Assert.assertTrue(stemRawWords.contains("fall"));
        Assert.assertTrue(stemRawWords.contains("hiss"));
        Assert.assertTrue(stemRawWords.contains("fizz"));
        Assert.assertTrue(stemRawWords.contains("fail"));
        Assert.assertTrue(stemRawWords.contains("file"));
        Assert.assertTrue(stemRawWords.contains("sky"));
    }
}

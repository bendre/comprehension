package unit.porter;

import org.junit.Assert;
import org.junit.Test;
import data.raw.process.stem.porter.PorterAlgorithm;

public class PorterRuleStep5Test {

    @Test
    public void porterStep5Test() {
        PorterAlgorithm porterAlgorithm = new PorterAlgorithm();
        String stemRawWords = porterAlgorithm.steamRawWords(getPorterStep5RawWords());
        assertPorterStep5RawWords(stemRawWords);
    }

    private String getPorterStep5RawWords() {
        //Input Raw Words
        StringBuilder rawWords = new StringBuilder();
        rawWords.append("probate ");
        rawWords.append("rate ");
        rawWords.append("cease ");
        rawWords.append("controll ");
        rawWords.append("roll ");

        return rawWords.toString();
    }

    private void assertPorterStep5RawWords(String stemRawWords) {
        Assert.assertTrue(stemRawWords.contains("probat"));
        Assert.assertTrue(stemRawWords.contains("rate"));
        Assert.assertTrue(stemRawWords.contains("ceas"));
        Assert.assertTrue(stemRawWords.contains("control"));
        Assert.assertTrue(stemRawWords.contains("roll"));
    }
}

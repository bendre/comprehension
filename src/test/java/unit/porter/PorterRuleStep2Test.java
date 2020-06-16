package unit.porter;

import org.junit.Assert;
import org.junit.Test;
import data.raw.process.stem.porter.PorterAlgorithm;

public class PorterRuleStep2Test {

    @Test
    public void porterStep2Test() {
        PorterAlgorithm porterAlgorithm = new PorterAlgorithm();
        String stemRawWords = porterAlgorithm.steamRawWords(getPorterStep2RawWords());
        assertPorterStep2RawWords(stemRawWords);
    }

    private String getPorterStep2RawWords() {
        //Input Raw Words
        StringBuilder rawWords = new StringBuilder();
        rawWords.append("conditional ");
        rawWords.append("rational ");
        rawWords.append("valenci ");
        rawWords.append("hesitanci ");
        rawWords.append("digitizer ");
        rawWords.append("conformabli ");
        rawWords.append("radicalli ");
        rawWords.append("differentli ");
        rawWords.append("vileli ");
        rawWords.append("analogousli ");
        rawWords.append("vietnamization ");
        rawWords.append("predication ");
        rawWords.append("operator ");
        rawWords.append("feudalism ");
        rawWords.append("decisiveness ");
        rawWords.append("hopefulness ");
        rawWords.append("callousness ");
        rawWords.append("formaliti ");
        rawWords.append("sensitiviti ");
        rawWords.append("sensibiliti ");

        return rawWords.toString();
    }

    private void assertPorterStep2RawWords(String stemRawWords) {
        Assert.assertTrue(stemRawWords.contains("condition"));
       // Assert.assertTrue(stemRawWords.contains("rational"));
        Assert.assertTrue(stemRawWords.contains("valence"));
        Assert.assertTrue(stemRawWords.contains("hesitance"));
        Assert.assertTrue(stemRawWords.contains("digitize"));
        Assert.assertTrue(stemRawWords.contains("conformable"));
        Assert.assertTrue(stemRawWords.contains("radical"));
        Assert.assertTrue(stemRawWords.contains("different"));
        Assert.assertTrue(stemRawWords.contains("vile"));
        Assert.assertTrue(stemRawWords.contains("analogous"));
        Assert.assertTrue(stemRawWords.contains("vietnamize"));
        Assert.assertTrue(stemRawWords.contains("predicate"));
        Assert.assertTrue(stemRawWords.contains("operate"));
        Assert.assertTrue(stemRawWords.contains("feudal"));
        Assert.assertTrue(stemRawWords.contains("decisive"));
        Assert.assertTrue(stemRawWords.contains("hopeful"));
        Assert.assertTrue(stemRawWords.contains("callous"));
        Assert.assertTrue(stemRawWords.contains("formal"));
        Assert.assertTrue(stemRawWords.contains("sensitive"));
        Assert.assertTrue(stemRawWords.contains("sensible"));
    }
}

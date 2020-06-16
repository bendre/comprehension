package unit.porter;

import org.junit.Assert;
import org.junit.Test;
import data.raw.process.stem.porter.PorterAlgorithm;

public class PorterRuleStep4Test {

    @Test
    public void porterStep4Test() {
        PorterAlgorithm porterAlgorithm = new PorterAlgorithm();
        String stemRawWords = porterAlgorithm.steamRawWords(getPorterStep4RawWords());
        assertPorterStep4RawWords(stemRawWords);
    }

    private String getPorterStep4RawWords() {
        //Input Raw Words
        StringBuilder rawWords = new StringBuilder();
        rawWords.append("revival ");
        rawWords.append("allowance ");
        rawWords.append("inference ");
        rawWords.append("airliner ");
        rawWords.append("gyroscopic ");
        rawWords.append("adjustable ");
        rawWords.append("defensible ");
        rawWords.append("irritant ");
        rawWords.append("replacement ");
        rawWords.append("adjustment ");
        rawWords.append("dependent ");
        rawWords.append("adoption ");
        rawWords.append("homologou ");
        rawWords.append("communism ");
        rawWords.append("activate ");
        rawWords.append("angulariti ");
        rawWords.append("hopefulness ");
        rawWords.append("homologous ");
        rawWords.append("effective ");
        rawWords.append("bowdlerize ");

        return rawWords.toString();
    }

    private void assertPorterStep4RawWords(String stemRawWords) {
        Assert.assertTrue(stemRawWords.contains("reviv"));
        Assert.assertTrue(stemRawWords.contains("allow"));
        Assert.assertTrue(stemRawWords.contains("infer"));
        Assert.assertTrue(stemRawWords.contains("airlin"));
        Assert.assertTrue(stemRawWords.contains("gyroscop"));
        Assert.assertTrue(stemRawWords.contains("adjust"));
        Assert.assertTrue(stemRawWords.contains("irrit"));
        Assert.assertTrue(stemRawWords.contains("replac"));
        Assert.assertTrue(stemRawWords.contains("adjust"));
        Assert.assertTrue(stemRawWords.contains("depend"));
        Assert.assertTrue(stemRawWords.contains("adopt"));
        Assert.assertTrue(stemRawWords.contains("homolog"));
        Assert.assertTrue(stemRawWords.contains("commun"));
        Assert.assertTrue(stemRawWords.contains("activ"));
        Assert.assertTrue(stemRawWords.contains("angular"));
        Assert.assertTrue(stemRawWords.contains("homolog"));
        Assert.assertTrue(stemRawWords.contains("effect"));
        Assert.assertTrue(stemRawWords.contains("bowdler"));
    }
}

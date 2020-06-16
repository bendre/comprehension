package data.raw.process.stem.porter;

import data.raw.process.stem.IStemmer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Porter algorithm is used to stem the words and bring the root word
 */
public final class PorterAlgorithm implements IStemmer {
    private static Logger logger = LoggerFactory.getLogger(PorterAlgorithm.class);

    @Override
    public String steamRawWords(final String rawWords) {
        PorterRuleStep1 porterRuleStep1 = new PorterRuleStep1();
        PorterRuleStep2 porterRuleStep2 = new PorterRuleStep2();
        PorterRuleStep3 porterRuleStep3 = new PorterRuleStep3();
        PorterRuleStep4 porterRuleStep4 = new PorterRuleStep4();
        PorterRuleStep5 porterRuleStep5 = new PorterRuleStep5();

        String stemRawWords = rawWords;
        stemRawWords = porterRuleStep1.stemmerProcess(stemRawWords);
        stemRawWords = porterRuleStep2.stemmerProcess(stemRawWords);
        stemRawWords = porterRuleStep3.stemmerProcess(stemRawWords);
        stemRawWords = porterRuleStep4.stemmerProcess(stemRawWords);
        stemRawWords = porterRuleStep5.stemmerProcess(stemRawWords);
        logger.info("StemRawWords {}", stemRawWords);
        return stemRawWords;
    }
}

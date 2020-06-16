package data.model;

import data.raw.process.noise.INoiseTextProcess;
import data.raw.process.noise.NoiseTextProcess;
import data.raw.process.stem.porter.PorterAlgorithm;


abstract class AbstractStemLine {
    /**
     *
     * Process each line and do following operation to clean up the text
     * 1. remove stopwords (is, was , the , in , out etc)
     * 1.1 stopword file is in the resources folder
     * 2. remove the punctuations from the sentence, except % and ,
     * 3. Use stemming algorithm, currently Porter Algorithm is used
     * @param line
     * @return
     */
    public String stemLine(final String line) {
        INoiseTextProcess noiseTextProcess = new NoiseTextProcess();
        PorterAlgorithm stemSentencePorterAlgorithm = new PorterAlgorithm();
        String cleanStopWordText = noiseTextProcess.cleanStopWordText(line);
        String cleanStopWordPunctuation = noiseTextProcess.cleanPunctuation(cleanStopWordText);
        return stemSentencePorterAlgorithm.steamRawWords(cleanStopWordPunctuation).trim();
    }
}

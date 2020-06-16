package data.raw.process.noise;

public class NoiseTextProcess implements INoiseTextProcess {

    @Override
    public String cleanStopWordText(final String rawText) {
        StopWord stopWord = new StopWord();
        return stopWord.cleanStopWord(rawText);
    }

    @Override
    public String cleanPunctuation(final String cleanStopWordText) {
        return cleanStopWordText.replaceAll("[[^a-zA-Z0-9]&&[^.%\\s]]", "");
    }
}

package data.raw.process.noise;

public interface INoiseTextProcess {

    String cleanStopWordText(final String rawText);

    String cleanPunctuation(final String cleanStopWordText);
}

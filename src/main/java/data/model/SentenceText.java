package data.model;

import data.configuration.ReadConfiguration;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
public class SentenceText extends TextBase implements IText {
    private List<Sentence> tokenizedStemSentences;

    public SentenceText() {
        ReadConfiguration readConfiguration = new ReadConfiguration();
        String text = super.readDataFromFile(readConfiguration.getSentenceQuestionAnswerFileNameProperty().getParagraphFileName());
        super.setTextCategory(TextCategory.SENTENCE);
        super.validateTextCharset(readConfiguration.getSentenceQuestionAnswerFileNameProperty().getCharset(), text);
        super.setFullText(text.trim());
        tokenizedStemSentences = saveTokenizedAndStemLine();
    }

    @Override
    List<Sentence> saveTokenizedAndStemLine() {
        List<String> tokenizedSentence = super.getTokenizedLines("[.]", super.getFullText());
        AtomicInteger sentenceId = new AtomicInteger(-1);
        return tokenizedSentence.stream()
                .map(line -> new Sentence(sentenceId.incrementAndGet(), line))
                .collect(Collectors.toList());
    }

    /**
     * Input
     * [Words], All sentence(QuestionMatching) | single sentence for AnswerMatching
     * <p>
     * Output:
     * | word2| sentence1, sentence2, sentence3|
     * | word2| sentence3, sentence5, sentence6|
     *
     * @param words
     * @return
     */
    public Map<String, HashSet<Integer>> wordOccurrencesWithSentences(final List<String> words) {
        try {
            return words.stream()
                    .collect(Collectors.toMap(word -> word, word -> addIfPresentInSentence(word), (word, wordDuplicate) -> word));
        } catch (Exception e) {
            throw new IllegalStateException("Exception occurred during wordOccurrences");
        }
    }


    private HashSet<Integer> addIfPresentInSentence(final String word) {
        try {
            return tokenizedStemSentences.stream()
                    .filter(sentence -> Pattern.compile(word + "\\b").matcher(sentence.getStemmedSentence()).find())
                    .map(Sentence::getSentenceId)
                    .collect(Collectors.toCollection(HashSet::new));
        } catch (Exception e) {
            throw new IllegalStateException("Exception occurred during word match in sentence" + e.getLocalizedMessage());
        }
    }

    @Override
    public SentenceText getITextImplementor() {
        return this;
    }
}

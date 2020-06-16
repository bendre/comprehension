package data.model;

import lombok.Data;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
public class Sentence extends AbstractStemLine implements ISentenceQuestionAnswer {
    private Integer sentenceId;
    private String originalSentence;
    private String stemmedSentence;


    Sentence(Integer sentenceIdentifier, String originalSentenceText) {
        String stemSentence = super.stemLine(originalSentenceText);
        this.sentenceId = sentenceIdentifier;
        this.originalSentence = originalSentenceText;
        this.stemmedSentence = stemSentence;
    }

    @Override
    public List<String> getTokenizedWords() {
        return new ArrayList<>(Arrays.asList(stemmedSentence.split(" ")));
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
    public Integer wordOccurrencesWithSentences(final List<String> words) {
        Map<String, String> matchedWords = words.stream()
                .filter(word -> Pattern.compile(word + "\\b").matcher(stemmedSentence).find())
                .collect(Collectors.toMap(word -> word, word -> word, (word, wordDuplicate) -> word));
        return matchedWords.keySet().size();
    }
}

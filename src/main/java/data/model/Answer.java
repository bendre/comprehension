package data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class Answer extends AbstractStemLine implements ISentenceQuestionAnswer {
    private Integer answerId;
    private String originalAnswer;
    private String stemmedAnswer;


    Answer(final Integer answerIdentifier, final String originalAnswerText) {
        String stemSentence = super.stemLine(originalAnswerText);
        this.answerId = answerIdentifier;
        this.originalAnswer = originalAnswerText;
        this.stemmedAnswer = stemSentence;
    }

    /**
     * return tokenized word list of the stemmed answer.
     *
     * @return
     */
    @Override
    public List<String> getTokenizedWords() {
        return new ArrayList<>(Arrays.asList(stemmedAnswer.split(" ")));
    }
}

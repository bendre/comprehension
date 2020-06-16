package data.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Question extends AbstractStemLine implements ISentenceQuestionAnswer {
    private Integer questionId;
    private String originalQuestion;
    private String stemmedQuestion;


    Question(Integer questionIdentifier, String originalQuestionText) {
        String stemQuestion = super.stemLine(originalQuestionText);
        this.questionId = questionIdentifier;
        this.originalQuestion = originalQuestionText;
        this.stemmedQuestion = stemQuestion;
    }

    @Override
    public List<String> getTokenizedWords() {
        return new ArrayList<>(Arrays.asList(stemmedQuestion.split(" ")));
    }
}

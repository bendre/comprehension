package data.model;

import data.configuration.ReadConfiguration;
import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
public class QuestionText extends TextBase implements IText {
    private List<Question> tokenizedStemQuestions;

    public QuestionText() {
        ReadConfiguration readConfiguration = new ReadConfiguration();
        String text = readDataFromFile(readConfiguration.getSentenceQuestionAnswerFileNameProperty().getQuestionFileName());
        setTextCategory(TextCategory.QUESTION);
        validateTextCharset(readConfiguration.getSentenceQuestionAnswerFileNameProperty().getCharset(), text);
        setFullText(text.trim());
        this.tokenizedStemQuestions = saveTokenizedAndStemLine();
    }

    @Override
    List<Question> saveTokenizedAndStemLine() {
        List<String> tokenizedQuestions = super.getTokenizedLines("\\?", super.getFullText());
        AtomicInteger questionId = new AtomicInteger(-1);
        return tokenizedQuestions.stream()
                .map(line -> new Question(Integer.valueOf(questionId.incrementAndGet()), line))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionText getITextImplementor() {
        return this;
    }
}

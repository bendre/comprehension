package data.model;

import data.configuration.ReadConfiguration;
import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
public class AnswerText extends TextBase implements IText {
    private List<Answer> tokenizedStemAnswers;

    public AnswerText() {
        ReadConfiguration readConfiguration = new ReadConfiguration();
        String text = readDataFromFile(readConfiguration.getSentenceQuestionAnswerFileNameProperty().getAnswerFileName());
        setTextCategory(TextCategory.ANSWER);
        validateTextCharset(readConfiguration.getSentenceQuestionAnswerFileNameProperty().getCharset(), text);
        setFullText(text.trim());
        this.tokenizedStemAnswers = saveTokenizedAndStemLine();
    }

    @Override
    List<Answer> saveTokenizedAndStemLine() {
        List<String> tokenizedAnswers = super.getTokenizedLines(";", super.getFullText());
        AtomicInteger answerId = new AtomicInteger(-1);
        return tokenizedAnswers.stream()
                .map(line -> new Answer(Integer.valueOf(answerId.incrementAndGet()), line))
                .collect(Collectors.toList());
    }

    @Override
    public AnswerText getITextImplementor() {
        return this;
    }

}

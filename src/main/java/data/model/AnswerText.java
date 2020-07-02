package data.model;

import data.configuration.ReadConfiguration;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
public class AnswerText extends TextBase {
    private List<Answer> tokenizedStemAnswers;

    public AnswerText(String charsets, String answerText) {
        ReadConfiguration readConfiguration = new ReadConfiguration();
        validateTextCharset(charsets, answerText);
        setFullText(answerText.trim());
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
}

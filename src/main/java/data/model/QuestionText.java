package data.model;

import data.configuration.ReadConfiguration;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
public class QuestionText extends TextBase {
    private List<Question> tokenizedStemQuestions;

    public QuestionText(String charsets, String questionText) {
        validateTextCharset(charsets, questionText);
        setFullText(questionText.trim());
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
}

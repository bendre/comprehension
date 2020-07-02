package data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ParagraphQuestionAnswer {
    private ParagraphText paragraphText;
    private QuestionText questionText;
    private AnswerText answerText;
}

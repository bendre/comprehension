package data.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SentenceQuestionAnswerFileNameProperty {
    /**
     * Variable name correspond to the property name in configuration.yml
     */
    private String charset;
    private String paragraphFileName;
    private String questionFileName;
    private String answerFileName;
}

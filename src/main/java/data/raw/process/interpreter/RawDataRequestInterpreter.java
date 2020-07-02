package data.raw.process.interpreter;

import data.comprehension.interpreter.ComprehensionInterpreter;
import data.model.ParagraphQuestionAnswer;
import model.QuestionAnswerPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RawDataRequestInterpreter {
    private static Logger logger = LoggerFactory.getLogger(RawDataRequestInterpreter.class);

    public List<QuestionAnswerPair> rawDataRequestInterpreter(final ParagraphQuestionAnswer paragraphQuestionAnswersModel) {
        ComprehensionInterpreter occurrenceInterpreter = new ComprehensionInterpreter(paragraphQuestionAnswersModel);
        List<QuestionAnswerPair> questionAnswerPair = occurrenceInterpreter.matchQuestionAnswer();
        logger.info("Question Answer Pair: {}", questionAnswerPair);
        return questionAnswerPair;
    }
}
